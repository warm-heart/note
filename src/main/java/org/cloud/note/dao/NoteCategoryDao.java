package org.cloud.note.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.dto.NoteAnalysis;
import org.cloud.note.entity.NoteCategory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author wangqianlong
 * @create 2019-12-02 18:42
 */
@Mapper
@Repository
public interface NoteCategoryDao {


    List<NoteCategory> listNoteCategoryByUserId(Integer userId);

    Integer updateNoteCategory(NoteCategory noteCategory);

    NoteCategory getCategoryByCategoryId(Integer categoryId);

    Integer countCategoryByUserId(Integer userId);

    Integer saveNoteCategory(NoteCategory noteCategory);


    NoteCategory getNoteCategoryByCategoryNameAndUserId(@Param(value = "categoryName") String categoryName,
                                                        @Param(value = "userId") Integer userId);

    Integer removeNoteCategoryByCategoryNameAndUserId(@Param(value = "categoryName") String categoryName,
                                                      @Param(value = "userId") Integer userId);


    List<NoteAnalysis> noteCategoryAnalysis();
}
