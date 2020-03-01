package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.VO.NoteShareVO;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.NoteShare;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:26
 */
@Mapper
@Repository
public interface NoteShareDao {

    Integer saveNoteShare(NoteShare noteShare);

    Integer removeNoteShareByNoteId(Integer noteId);

    Integer incrementLoveCount(Integer noteId);


    /**
     * 分页获取数据
     *
     * @param page 页数开始的下标
     * @param size 每页数量
     * @return
     */
    List<NoteShareVO> listNoteShareByPage(@Param(value = "page") Integer page,
                                          @Param(value = "size") Integer size);


    /**
     * @return 获取所有条数
     */
    Integer countNoteShare();
}
