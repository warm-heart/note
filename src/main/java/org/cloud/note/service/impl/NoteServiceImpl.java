package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.cloud.note.VO.NoteVO;
import org.cloud.note.dao.UserDao;
import org.cloud.note.dto.*;
import org.cloud.note.dao.NoteDao;
import org.cloud.note.entity.*;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.NoteException;
import org.cloud.note.exception.UserException;
import org.cloud.note.service.NoteCategoryService;
import org.cloud.note.service.NoteService;
import org.cloud.note.service.NoteShareService;
import org.cloud.note.service.NoteTagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:25
 */
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteDao noteDao;
    @Autowired
    StringRedisTemplate stringRedisTemplates;
    @Autowired
    NoteCategoryService noteCategoryService;
    @Autowired
    NoteTagService noteTagService;
    @Autowired
    NoteShareService noteShareService;
    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public ServiceResult<NoteDTO> listNoteByPageAndUserId(Integer page, Integer size, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noteDao.countNoteByUserId(userId);
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error(ResultEnum.YOUR_NOTE_IS_EMPTY.getMessage());
        }
        List<Note> noteList = noteDao.listNoteByUserId(page, size, userId);
        for (Note note : noteList) {
            String str = note.getNoteContext();
            str = str.replaceAll("<.+?>", "");
            str = str.replaceAll("<a>\\s*|\t|\r|\n|&nbsp;|</a>", "");
            note.setNoteContext(str);
        }
        NoteDTO noteDTO = new NoteDTO(noteList, total);
        return ServiceResult.success(noteDTO);
    }

    @Override
    @Transactional
    public ServiceResult<String> saveNote(NoteVO noteVO, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        // 1 根据userId和笔记分类name查出笔记分类
        NoteCategory noteCategory = noteCategoryService.
                getNoteCategoryBycategoryNameAndUserId(noteVO.getCategoryName(), userId).getResult();
        if (noteCategory == null)
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        // 2 组装note 插入到数据库
        Note note = new Note();
        note.setNoteContext(noteVO.getNoteContext());
        note.setNoteDescription(noteVO.getNoteDescription());
        note.setNoteTitle(noteVO.getNoteTitle());
        note.setUserId(userId);
        note.setCategoryId(noteCategory.getCategoryId());
        Integer res = noteDao.saveNote(note);
        //判断插入失败
        if (res != 1) {
            throw new NoteException(ResultEnum.CREATE_NOTE_FAIL);
        }
        //创建标签
        if (CollectionUtils.isEmpty(noteVO.getNoteTagList())) {
            log.info("笔记标签为空： {}");
            return ServiceResult.success(ResultEnum.CREATE_NOTE_SUCCESS.getMessage());
        }
        for (String s : noteVO.getNoteTagList()) {
            noteTagService.saveNoteTag(s, note.getNoteId());
        }
        return ServiceResult.success(ResultEnum.CREATE_NOTE_SUCCESS.getMessage());

    }

    @Override
    @Transactional
    public ServiceResult<NoteDetailDTO> getNoteByNoteId(Integer noteId) {
        NoteDetailDTO noteDetailDTO = (NoteDetailDTO) redisTemplate.opsForValue().get(noteId.toString());
        if (noteDetailDTO == null) {
            // 1 查出Note
            NoteDetailDTO noteDetailDTO1 = new NoteDetailDTO();
            Note note = noteDao.getNoteByNoteId(noteId);
            if (note == null) {
                throw new NoteException(ResultEnum.NOTE_NOT_FOUND);
            }
            // 2 根据noteId查出标签
            List<NoteTag> noteTagList = noteTagService.listNoteTagByNoteId(noteId);
            // 3 根据noteId查出笔记所属分类
            NoteCategory noteCategory = noteCategoryService.getCategoryByCategoryId(note.getCategoryId()).getResult();
            // 4查出用户
            User user = userDao.getUserById(note.getUserId());

            // 5组装DTO返回前端
            noteDetailDTO1.setUserName(user.getUserName());
            noteDetailDTO1.setNote(note);
            noteDetailDTO1.setNoteCategory(noteCategory);
            List<String> labels = noteTagList.stream().map(e -> e.getNoteLabel()).collect(Collectors.toList());
            noteDetailDTO1.setNoteTagList(labels);
            redisTemplate.opsForValue().set(noteId.toString(), noteDetailDTO1, 1, TimeUnit.HOURS);
            return ServiceResult.success(noteDetailDTO1);
        }
        return ServiceResult.success(noteDetailDTO);

    }

    @Override
    @Transactional
    public ServiceResult<String> removeNoteByNoteId(Integer noteId) {
        Note note = noteDao.getNoteByNoteId(noteId);
        if (note == null) {
            throw new NoteException(ResultEnum.NOTE_NOT_FOUND);
        }
        // 1 根据NoteId删除Note
        Integer res = noteDao.removeNoteByNoteId(noteId);
        // 2 删除对应的标签
        noteTagService.removeNoteTagByNoteId(noteId);
        if (note.getShareStatus() == 1)
            // 3删除分享表
            try {
                noteShareService.removeNoteShareByNoteId(noteId);
            } catch (Exception e) {
                throw new NoteException(ResultEnum.REMOVE_NOTE_FAIL);
            }

        // 4 删除redis
        redisTemplate.delete(noteId.toString());
        if (res == 1) {
            return ServiceResult.success(ResultEnum.REMOVE_NOTE_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.REMOVE_NOTE_FAIL);
    }

    @Override
    @Transactional
    public ServiceResult<String> updateNote(NoteVO noteVO, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));

        //1 删除笔记标签
        noteTagService.removeNoteTagByNoteId(noteVO.getNoteId());
        //2 添加新标签
        if (!CollectionUtils.isEmpty(noteVO.getNoteTagList())) {
            for (String s : noteVO.getNoteTagList()) {
                noteTagService.saveNoteTag(s, noteVO.getNoteId());
            }
        }

        NoteCategory noteCategory = noteCategoryService
                .getNoteCategoryBycategoryNameAndUserId(noteVO.getCategoryName(), userId).getResult();
        //3 修改Note
        Note note = noteDao.getNoteByNoteId(noteVO.getNoteId());
        if (note == null)
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        note.setNoteTitle(noteVO.getNoteTitle());
        note.setNoteDescription(noteVO.getNoteDescription());
        note.setNoteContext(noteVO.getNoteContext());
        note.setCategoryId(noteCategory.getCategoryId());
        Integer res = noteDao.updateNote(note);
        //4 删除redis
        redisTemplate.delete(noteVO.getNoteId().toString());
        if (res == 1) {
            return ServiceResult.success(ResultEnum.UPDATE_NOTE_SUCCESS.getMessage());
        }
        //修改失败事务回滚
        throw new NoteException(ResultEnum.UPDATE_NOTE_FAIL);
    }

    @Override
    public ServiceResult<List<Note>> listNoteByCategoryIdAndUserId(Integer categoryId, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        List<Note> noteList = noteDao.listNoteByCategoryIdAndUserId(categoryId, userId);
        if (CollectionUtils.isEmpty(noteList)) {
            return ServiceResult.error(ResultEnum.NOTE_NOT_FOUND.getMessage());
        }
        for (Note note : noteList) {
            String str = note.getNoteContext();
            str = str.replaceAll("<.+?>", "");
            str = str.replaceAll("<a>\\s*|\t|\r|\n|&nbsp;|</a>", "");
            note.setNoteContext(str);
        }
        return ServiceResult.success(noteList);
    }

    @Override
    @Transactional
    public ServiceResult<String> shareNote(Integer noteId) {
        redisTemplate.delete(noteId.toString());
        // 1 设置状态为1 已分享；
        Note note = noteDao.getNoteByNoteId(noteId);
        if (note == null)
            throw new NoteException(ResultEnum.NOTE_NOT_FOUND);
        note.setShareStatus(1);
        Integer res = noteDao.updateNote(note);

        //2 添加到分享表
        NoteShare noteShare = new NoteShare();
        noteShare.setNoteId(noteId);
        noteShare.setUserId(note.getUserId());
        boolean flag = noteShareService.saveNoteShare(noteShare);
        if (res == 1 && flag) {
            redisTemplate.delete(noteId.toString());
            return ServiceResult.success("分享成功");
        }
        throw new NoteException("分享失败");
    }


    @Override
    @Transactional
    public ServiceResult<String> cancelShareNote(Integer noteId) {
        // 1 设置状态为0 未分享；
        Note note = noteDao.getNoteByNoteId(noteId);
        if (note == null)
            throw new NoteException(ResultEnum.NOTE_NOT_FOUND);
        note.setShareStatus(0);
        Integer res = noteDao.updateNote(note);
        //2 删除分享表
        noteShareService.removeNoteShareByNoteId(noteId);
        if (res == 1) {
            redisTemplate.delete(noteId.toString());
            return ServiceResult.success("取消分享成功");
        }
        throw new NoteException("取消分享失败");
    }

    @Override
    public ServiceResult<List<Note>> searchByNoteTitleAndUserId(String noteTitle, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        List<Note> noteList = noteDao.listNoteByNoteTitleAndUserId(noteTitle, userId);
        if (CollectionUtils.isEmpty(noteList)) {
            return ServiceResult.error(ResultEnum.NOTE_NOT_FOUND.getMessage());
        }
        for (Note note : noteList) {
            String str = note.getNoteContext();
            str = str.replaceAll("<.+?>", "");
            str = str.replaceAll("<a>\\s*|\t|\r|\n|&nbsp;|</a>", "");
            note.setNoteContext(str);
        }
        return ServiceResult.success(noteList);
    }


    @Override
    public ServiceResult<List<Note>> searchByNoteTitle(String noteTitle) {
        List<Note> noteList = noteDao.listNoteByNoteTitle(noteTitle);
        if (CollectionUtils.isEmpty(noteList)) {
            return ServiceResult.error(ResultEnum.NOTE_NOT_FOUND.getMessage());
        }
        for (Note note : noteList) {
            String str = note.getNoteContext();
            str = str.replaceAll("<.+?>", "");
            str = str.replaceAll("<a>\\s*|\t|\r|\n|&nbsp;|</a>", "");
            note.setNoteContext(str);
        }
        return ServiceResult.success(noteList);
    }

    @Override
    public ServiceResult<NoteDTO> listNoteByPage(Integer page, Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noteDao.countNote();
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error("无笔记");
        }
        List<Note> notes = noteDao.listNoteByPage(page, size);

        NoteDTO noteDTO = new NoteDTO(notes, total);
        return ServiceResult.success(noteDTO);
    }


    @Override
    public ServiceResult<NoteDTO> listLockNoteByPage(Integer page, Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noteDao.countLockNote();
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error("无笔记");
        }
        List<Note> notes = noteDao.listLockNoteByPage(page, size);

        NoteDTO noteDTO = new NoteDTO(notes, total);
        return ServiceResult.success(noteDTO);
    }


    @Override
    public ServiceResult<String> deBlockNote(Integer noteId) {
        log.info("解封Id：{} 的笔记", noteId);
        Integer res = noteDao.deBlockNote(noteId);
        if (1 == res) {
            redisTemplate.delete(noteId.toString());
            return ServiceResult.success(ResultEnum.NOTE_DE_BLOCK_SUCCESS.getMessage());
        }
        throw new UserException(ResultEnum.NOTE_DE_BLOCK_FAIL);
    }

    @Override
    public ServiceResult<String> lockNote(Integer noteId) {
        log.info("封禁Id：{} 的笔记", noteId);
        Integer res = noteDao.lockNote(noteId);
        if (1 == res) {
            redisTemplate.delete(noteId.toString());
            return ServiceResult.success(ResultEnum.NOTE_LOCK_SUCCESS.getMessage());
        }
        throw new UserException(ResultEnum.NOTE_LOCK_FAIL);
    }

    @Override
    @Transactional
    public ServiceResult<String> moveCategory(Integer noteId, Integer categoryId) {
        Note note = noteDao.getNoteByNoteId(noteId);
        if (note == null) {
            throw new NoteException(ResultEnum.NOTE_NOT_FOUND);
        }
        note.setCategoryId(categoryId);
        Integer res = noteDao.updateNote(note);
        if (res == 1) {
            redisTemplate.delete(noteId.toString());
            return ServiceResult.success("移动成功");
        }
        throw new NoteException("移动笔记分类失败");
    }

    @Override
    public ServiceResult<NoteAnalysisDTO> noteAnalysis() {


        List<NoteAnalysis> noteTagAnalysis = noteTagService.noteTagAnalysis();
        List<NoteAnalysis> noteShareAnalysis = noteShareService.noteShareAnalysis();
        List<NoteAnalysis> noteCategoryAnalysis = noteCategoryService.noteCategoryAnalysis();
        List<NoteAnalysis> noteUserAnalysis = noteDao.noteUserAnalysis();


        List<String> XTagAnalysis;
        List<String> XShareAnalysis;
        List<String> XCategoryAnalysis;
        List<String> XUserAnalysis;
        List<Integer> YTagAnalysis;
        List<Integer> YShareAnalysis;
        List<Integer> YCategoryAnalysis;
        List<Integer> YUserAnalysis;

        XTagAnalysis = noteTagAnalysis.stream().map(e -> e.getName()).collect(Collectors.toList());
        XShareAnalysis = noteShareAnalysis.stream().map(e -> e.getName()).collect(Collectors.toList());
        XCategoryAnalysis = noteCategoryAnalysis.stream().map(e -> e.getName()).collect(Collectors.toList());
        XUserAnalysis = noteUserAnalysis.stream().map(e -> e.getName()).collect(Collectors.toList());


        YShareAnalysis = noteShareAnalysis.stream().map(e -> e.getNum()).collect(Collectors.toList());
        YCategoryAnalysis = noteCategoryAnalysis.stream().map(e -> e.getNum()).collect(Collectors.toList());
        YUserAnalysis = noteUserAnalysis.stream().map(e -> e.getNum()).collect(Collectors.toList());

        NoteAnalysisDTO noteAnalysisDTO = new NoteAnalysisDTO();

        noteAnalysisDTO.setXTagAnalysis(XTagAnalysis);
        noteAnalysisDTO.setXShareAnalysis(XShareAnalysis);
        noteAnalysisDTO.setXCategoryAnalysis(XCategoryAnalysis);
        noteAnalysisDTO.setXUserAnalysis(XUserAnalysis);


        noteAnalysisDTO.setYShareAnalysis(YShareAnalysis);
        noteAnalysisDTO.setYCategoryAnalysis(YCategoryAnalysis);
        noteAnalysisDTO.setYUserAnalysis(YUserAnalysis);

        noteAnalysisDTO.setNoteTagAnalysis(noteTagAnalysis);

        return ServiceResult.success(noteAnalysisDTO);
    }

}
