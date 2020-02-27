package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
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
     * 分页获取用户的笔记
     *
     * @param page 页数开始的下标
     * @param size 每页数量
     * @return
     */
    List<Note> findAllNoteByPageAndUserId(@Param(value = "page") Integer page,
                                          @Param(value = "size") Integer size,
                                          @Param(value = "userId") Integer userId);


    /**
     * @return 获取用户的所有条数
     */
    Integer getTotalByUserId(Integer userId);

    /**
     * 获取总数
     *
     * @return
     */
    Integer getTotal();


    Integer saveNote(Note note);

    Integer removeByNoteId(Integer noteId);

    List<Note> findByCategoryIdAndUserId(@Param(value = "categoryId") Integer categoryId,
                                         @Param(value = "userId") Integer userId);


    List<Note> findByTime(@Param(value = "startTime") Date startTime,
                          @Param(value = "endTime") Date endTime);

    /**
     * select * from  note_info where note_title like CONCAT('%',#{keyWord},'%')
     * select * from  note_info where note_title like '%${keyWord}%'
     *
     * @param keyWord
     * @return
     */
    List<Note> findByKeyWord(@Param(value = "keyWord") String keyWord,
                             @Param(value = "userId") Integer userId);

    /**
     * 获取所有笔记
     *
     * @param page
     * @param size
     * @return
     */
    List<Note> findAllNoteByPage(@Param(value = "page") Integer page,
                                 @Param(value = "size") Integer size);

    Integer lockNote(Integer noteId);

    Integer deBlockNote(Integer noteId);

    List<Note> findAllLockNoteByPage(@Param(value = "page") Integer page,
                                 @Param(value = "size") Integer size);

    Integer getLockTotal();
}
