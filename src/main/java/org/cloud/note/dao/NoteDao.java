package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:59
 */
@Mapper
@Repository
public interface NoteDao {

    /**
     * @param noteTitle 笔记名
     * @return
     */
    Note findByNoteTitle(String noteTitle);

    /**
     * @param noteId 笔记ID
     * @return
     */
    Note findByNoteId(Integer noteId);

    /**
     * 修改笔记信息
     *
     * @param note 笔记
     * @return
     */
    Integer updateNote(Note note);


    /**
     * 分页获取数据
     *
     * @param page 页数开始的下标
     * @param size 每页数量
     * @return
     */
    List<Note> findAllNoteByPage(@Param(value = "page") Integer page,
                                 @Param(value = "size") Integer size,
                                 @Param(value = "userId") Integer userId);


    /**
     * @return 获取所有条数
     */
    Integer getTotal(Integer userId);

    Integer saveNote(Note note);

    Integer removeByNoteId(Integer noteId);

    List<Note> findByCategoryIdAndUserId(@Param(value = "categoryId") Integer categoryId,
                                         @Param(value = "userId") Integer userId);
}
