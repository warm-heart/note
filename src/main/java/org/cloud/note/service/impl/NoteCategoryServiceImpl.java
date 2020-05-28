package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dao.NoteDao;
import org.cloud.note.dto.NoteAnalysis;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dao.NoteCategoryDao;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.NoteCategory;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteCategoryService;
import org.cloud.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author wangqianlong
 * @create 2020-01-03 15:57
 */
@Service
@Slf4j
public class NoteCategoryServiceImpl implements NoteCategoryService {
    @Autowired
    private NoteCategoryDao noteCategoryDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    NoteService noteService;
    @Autowired
    private NoteDao noteDao;


    @Override
    public ServiceResult<List<NoteCategory>> listNoteCategoryByUserId(String token) {
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get(token));
        List<NoteCategory> noteCategoryList = noteCategoryDao.listNoteCategoryByUserId(userId);
        if (CollectionUtils.isEmpty(noteCategoryList))
            throw new NoteException(ResultEnum.NOTE_CATEGORY_IS_EMPTY);
        return ServiceResult.success(noteCategoryList);
    }


    @Override
    public ServiceResult<NoteCategory> getNoteCategoryBycategoryNameAndUserId(String categoryName, Integer userId) {
        NoteCategory noteCategory = noteCategoryDao.getNoteCategoryByCategoryNameAndUserId(categoryName, userId);
        if (noteCategory == null) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        }
        return ServiceResult.success(noteCategory);
    }

    @Override
    public ServiceResult<NoteCategory> getCategoryByCategoryId(Integer categoryId) {
        NoteCategory noteCategory = noteCategoryDao.getCategoryByCategoryId(categoryId);
        if (noteCategory == null) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        }
        return ServiceResult.success(noteCategory);
    }

    @Override
    @Transactional
    public ServiceResult<String> saveNoteCategory(String categoryName, String categoryDescription,
                                                  Integer userId) {
        NoteCategory category = noteCategoryDao.getNoteCategoryByCategoryNameAndUserId(categoryName, userId);
        if (category != null) {
            return ServiceResult.error("此分类已经存在");
        }

        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setCategoryName(categoryName);
        noteCategory.setCategoryDescription(categoryDescription);
        noteCategory.setUserId(userId);
        Integer res = noteCategoryDao.saveNoteCategory(noteCategory);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.CREATE_NOTE_CATEGORY_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.CREATE_NOTE_CATEGORY_FAIL);
    }

    @Override
    @Transactional
    public ServiceResult<String> removeNoteCategory(String categoryName, Integer userId) {
        //1 如果笔记分类少于 1 则无法删除
        Integer count = noteCategoryDao.countCategoryByUserId(userId);
        NoteCategory noteCategory = noteCategoryDao.getNoteCategoryByCategoryNameAndUserId(categoryName, userId);
        if (count <= 1) {
            return ServiceResult.error(ResultEnum.NOTE_CATEGORY_NOT_LESS_ONE.getMessage());
        }
        if (noteCategory == null) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        }

        //2 删除笔记分类下的笔记
        List<Note> notes = noteDao.listNoteByCategoryIdAndUserId(noteCategory.getCategoryId(), userId);
        try {
            for (Note note : notes) {
                noteService.removeNoteByNoteId(note.getNoteId());
            }
        } catch (Exception e) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_REMOVE_FAIL);
        }
        //3  删除笔记分类
        Integer res = noteCategoryDao.removeNoteCategoryByCategoryNameAndUserId(categoryName, userId);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.NOTE_CATEGORY_REMOVE_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.NOTE_CATEGORY_REMOVE_FAIL);
    }


    @Override
    @Transactional
    public ServiceResult<String> updateNoteCategory(NoteCategory noteCategory) {

        NoteCategory noteCategory1 = getCategoryByCategoryId(noteCategory.getCategoryId()).getResult();
        noteCategory1.setCategoryDescription(noteCategory.getCategoryDescription());
        noteCategory1.setCategoryName(noteCategory.getCategoryName());
        Integer res = noteCategoryDao.updateNoteCategory(noteCategory1);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.NOTE_CATEGORY_UPDATE_SUCCESS.getMessage());
        }
        throw new NoteException(ResultEnum.NOTE_CATEGORY_UPDATE_FAIL);
    }

    @Override
    public List<NoteAnalysis> noteCategoryAnalysis() {
        return noteCategoryDao.noteCategoryAnalysis();
    }

}
