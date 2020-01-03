package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.dao.NoteDao;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.NoteCategory;
import org.cloud.note.entity.NoteTag;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteCategoryService;
import org.cloud.note.service.NoteService;
import org.cloud.note.service.NoteTagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
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

    @Override
    @Transactional
    public ServiceResult<NoteDTO> getNoteByPage(Integer page, Integer size, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noteDao.getTotal(userId);
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error(ResultEnum.YOUR_NOTE_IS_EMPTY.getMessage());
        }
        List<Note> noteList = noteDao.findAllNoteByPage(page, size, userId);
        NoteDTO noteDTO = new NoteDTO(noteList, total);
        return ServiceResult.success(noteDTO);
    }

    @Override
    @Transactional
    public ServiceResult<String> createNote(NoteDetailDTO noteDetailDTO, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));
        // 1 根据userId和笔记分类name查出笔记分类
        NoteCategory noteCategory = noteCategoryService.
                getNoteCategoryByNameAndUserId(noteDetailDTO.getCategoryName(), userId).getResult();
        // 2 组装note 插入到数据库
        Note note = new Note();
        note.setNoteContext(noteDetailDTO.getNoteContext());
        note.setNoteDescription(noteDetailDTO.getNoteDescription());
        note.setNoteTitle(noteDetailDTO.getNoteTitle());
        note.setUserId(userId);
        note.setCategoryId(noteCategory.getCategoryId());
        Integer res = noteDao.saveNote(note);
        //判断插入失败
        if (res != 1) {
            throw new NoteException(ResultEnum.CREATE_NOTE_FAIL);
        }
        //创建标签
        if (CollectionUtils.isEmpty(noteDetailDTO.getNoteTagList())) {
            log.info("笔记标签为空： {}");
            return ServiceResult.success(ResultEnum.CREATE_NOTE_SUCCESS.getMessage());
        }
        for (String s : noteDetailDTO.getNoteTagList()) {
            noteTagService.saveNoteTag(s, note.getNoteId());
        }
        return ServiceResult.success(ResultEnum.CREATE_NOTE_SUCCESS.getMessage());

    }

    @Override
    @Transactional
    public ServiceResult<NoteDetailDTO> getNoteById(Integer noteId) {
        // 1 查出Note
        NoteDetailDTO noteDetailDTO = new NoteDetailDTO();
        Note note = noteDao.findByNoteId(noteId);
        // 2 根据noteId查出标签
        List<NoteTag> noteTagList = noteTagService.getByNoteId(noteId);
        // 3 根据noteId查出笔记所属分类
        NoteCategory noteCategory = noteCategoryService.getNoteCategoryById(note.getCategoryId()).getResult();
        //4 组装DTO返回前端
        noteDetailDTO.setNoteTitle(note.getNoteTitle());
        noteDetailDTO.setNoteDescription(note.getNoteDescription());
        noteDetailDTO.setNoteContext(note.getNoteContext());
        List<String> labels = noteTagList.stream().map(e -> e.getNoteLabel()).collect(Collectors.toList());
        noteDetailDTO.setNoteTagList(labels);
        noteDetailDTO.setCategoryName(noteCategory.getCategoryName());
        return ServiceResult.success(noteDetailDTO);

    }

    @Override
    @Transactional
    public ServiceResult<String> removeByNoteId(Integer noteId) {
        // 1 根据NoteId删除Note
        Integer res = noteDao.removeByNoteId(noteId);
        // 2 删除对应的标签
        noteTagService.removeByNoteId(noteId);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.REMOVE_NOTE_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.REMOVE_NOTE_FAIL);
    }

    @Override
    @Transactional
    public ServiceResult<String> updateNote(NoteDetailDTO noteDetailDTO, String token) {
        Integer userId = Integer.valueOf(stringRedisTemplates.opsForValue().get(token));

        //1 删除笔记标签
        noteTagService.removeByNoteId(noteDetailDTO.getNoteId());

        //2 添加新标签
        if (!CollectionUtils.isEmpty(noteDetailDTO.getNoteTagList())) {
            for (String s : noteDetailDTO.getNoteTagList()) {
                noteTagService.saveNoteTag(s, noteDetailDTO.getNoteId());
            }
        }

        NoteCategory noteCategory = noteCategoryService
                .getNoteCategoryByNameAndUserId(noteDetailDTO.getCategoryName(), userId).getResult();
        //3 修改Note
        Note note = noteDao.findByNoteId(noteDetailDTO.getNoteId());

        note.setNoteTitle(noteDetailDTO.getNoteTitle());
        note.setNoteDescription(noteDetailDTO.getNoteDescription());
        note.setNoteContext(noteDetailDTO.getNoteContext());
        note.setCategoryId(noteCategory.getCategoryId());

        Integer res = noteDao.updateNote(note);


        if (res == 1) {
            return ServiceResult.success(ResultEnum.UPDATE_NOTE_SUCCESS.getMessage());
        }

        //修改失败事务回滚
        throw new NoteException(ResultEnum.UPDATE_NOTE_FAIL);
    }


}
