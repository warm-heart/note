package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.dao.NoteCategoryDao;
import org.cloud.note.entity.NoteCategory;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteCategoryService;
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


    @Override
    public ServiceResult<List<NoteCategory>> getNoteCategoryByUserId(String token) {
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get(token));
        List<NoteCategory> noteCategoryList = noteCategoryDao.findByUserId(userId);
        if (CollectionUtils.isEmpty(noteCategoryList))
            throw new NoteException(ResultEnum.NOTE_CATEGORY_IS_EMPTY);
       /* List<String> categoryNames = noteCategoryList.stream()
                .map(e -> e.getCategoryName()).collect(Collectors.toList());*/
        return ServiceResult.success(noteCategoryList);
    }


    @Override
    public ServiceResult<NoteCategory> getNoteCategoryByNameAndUserId(String categoryName, Integer userId) {
        NoteCategory noteCategory = noteCategoryDao.getNoteCategoryByNameAndUserId(categoryName, userId);
        if (noteCategory == null) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        }
        return ServiceResult.success(noteCategory);
    }

    @Override
    public ServiceResult<NoteCategory> getNoteCategoryById(Integer categoryId) {
        NoteCategory noteCategory = noteCategoryDao.findByCategoryId(categoryId);
        if (noteCategory == null) {
            throw new NoteException(ResultEnum.NOTE_CATEGORY_NOT_FOUND);
        }
        return ServiceResult.success(noteCategory);
    }

    @Override
    @Transactional
    public ServiceResult createNoteCategory(String categoryName, String categoryDescription,
                                            Integer userId) {
        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setCategoryName(categoryName);
        noteCategory.setCategoryDescription(categoryDescription);
        noteCategory.setUserId(userId);
        Integer res = noteCategoryDao.saveNoteCategory(noteCategory);
        if (res == 1) {
            return ServiceResult.success(ResultEnum.CREATE_NOTE_CATEGORY_SUCCESS);
        }
        throw new NoteException(ResultEnum.CREATE_NOTE_CATEGORY_FAIL);
    }

}
