package org.cloud.note.service;

import org.cloud.note.VO.ServiceResult;
import org.cloud.note.entity.NoteCategory;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 15:56
 */

public interface NoteCategoryService {
    ServiceResult<List<NoteCategory>> getNoteCategoryByUserId(String token);

    ServiceResult<NoteCategory> getNoteCategoryByNameAndUserId(String categoryName, Integer userId);

    ServiceResult<NoteCategory> getNoteCategoryById(Integer categoryId);

    ServiceResult createNoteCategory(String category, String categoryDescription, Integer userId);
}
