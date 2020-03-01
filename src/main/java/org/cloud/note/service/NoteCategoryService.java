package org.cloud.note.service;

import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.NoteCategory;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 15:56
 */

public interface NoteCategoryService {
    /**
     * 查找用户的笔记分类
     *
     * @param token token
     * @return
     */
    ServiceResult<List<NoteCategory>> listNoteCategoryByUserId(String token);

    /**
     * 根据笔记分类名查找用户的笔记分类
     *
     * @param categoryName
     * @param userId
     * @return
     */
    ServiceResult<NoteCategory> getNoteCategoryBycategoryNameAndUserId(String categoryName, Integer userId);

    /**
     * 通过ID查找笔记分类信息
     *
     * @param categoryId
     * @return
     */
    ServiceResult<NoteCategory> getCategoryByCategoryId(Integer categoryId);

    /**
     * 创建笔记分类
     *
     * @param categoryName
     * @param categoryDescription
     * @param userId
     * @return
     */
    ServiceResult<String> saveNoteCategory(String categoryName, String categoryDescription, Integer userId);

    /**
     * 删除笔记分类
     *
     * @param categoryName
     * @param userId
     * @return
     */
    ServiceResult<String> removeNoteCategory(String categoryName, Integer userId);

    /**
     * 修改笔记分类信息
     *
     * @param noteCategory
     * @return
     */
    ServiceResult<String> updateNoteCategory(NoteCategory noteCategory);
}
