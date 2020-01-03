package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.entity.NoteTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:26
 */
@Mapper
@Repository
public interface NoteTagDao {

    List<NoteTag> findByNoteId(Integer noteId);

    NoteTag findByNoteTagId(Integer tagId);

    Integer updateNoteTag(NoteTag noteTag);

    List<NoteTag> findAllNoteTagByPage(@Param(value = "page") Integer page,
                                       @Param(value = "size") Integer size);

    Integer saveNoteTag(NoteTag noteTag);

    Integer getTotal();

    Integer removeByNoteId(Integer noteId);
}
