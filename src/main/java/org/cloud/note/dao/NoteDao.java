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
     * 根据笔记标题获取笔记
     *
     * @param noteTitle 笔记名
     * @return
     */
    Note getNoteByNoteTitle(String noteTitle);

    /**
     * 根据笔记ID获取笔记
     *
     * @param noteId 笔记ID
     * @return
     */
    Note getNoteByNoteId(Integer noteId);

    /**
     * 修改笔记信息
     *
     * @param note 笔记
     * @return
     */
    Integer updateNote(Note note);


    /**
     * 用户分页获取笔记
     *
     * @param page 页数开始的下标
     * @param size 每页数量
     * @return
     */
    List<Note> listNoteByUserId(@Param(value = "page") Integer page,
                                @Param(value = "size") Integer size,
                                @Param(value = "userId") Integer userId);


    /**
     * @return 获取用户的所有条数
     */
    Integer countNoteByUserId(Integer userId);

    /**
     * 获取所有笔记总数
     *
     * @return
     */
    Integer countNote();


    /**
     * 保存笔记
     *
     * @param note
     * @return
     */
    Integer saveNote(Note note);

    /**
     * 通过笔记ID删除笔记
     *
     * @param noteId
     * @return
     */
    Integer removeNoteByNoteId(Integer noteId);

    /**
     * 通过用户ID 和分类ID获取笔记
     *
     * @param categoryId
     * @param userId
     * @return
     */
    List<Note> listNoteByCategoryIdAndUserId(@Param(value = "categoryId") Integer categoryId,
                                             @Param(value = "userId") Integer userId);


    /**
     * 查询时间端  测试用
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<Note> findByTime(@Param(value = "startTime") Date startTime,
                          @Param(value = "endTime") Date endTime);

    /**
     * select * from  note_info where note_title like CONCAT('%',#{keyWord},'%')
     * select * from  note_info where note_title like '%${keyWord}%'
     * 根据用户ID和笔记标题获取笔记
     *
     * @param noteTitle
     * @return
     */
    List<Note> listNoteByNoteTitleAndUserId(@Param(value = "noteTitle") String noteTitle,
                                            @Param(value = "userId") Integer userId);

    /**
     * 获取所有笔记
     *
     * @param page
     * @param size
     * @return
     */
    List<Note> listNoteByPage(@Param(value = "page") Integer page,
                              @Param(value = "size") Integer size);

    /**
     * 锁定笔记
     *
     * @param noteId
     * @return
     */
    Integer lockNote(Integer noteId);

    /**
     * 截所笔记
     *
     * @param noteId
     * @return
     */
    Integer deBlockNote(Integer noteId);

    /** 获取所有封禁笔记
     * @param page
     * @param size
     * @return
     */
    List<Note> listLockNoteByPage(@Param(value = "page") Integer page,
                                  @Param(value = "size") Integer size);

    /** 所有封禁笔记总数
     * @return
     */
    Integer countLockNote();


    List<Note> listNoteByNoteTitle(String keyWord);
}
