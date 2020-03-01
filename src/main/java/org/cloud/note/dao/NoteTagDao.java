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

    List<NoteTag> listNoteTagByNoteId(Integer noteId);

    NoteTag getNOteTagByNoteTagId(Integer tagId);

    Integer updateNoteTag(NoteTag noteTag);


    Integer saveNoteTag(NoteTag noteTag);

    Integer removeNoteTagByNoteId(Integer noteId);

    Integer countNoteTag();

    List<NoteTag> listNoteTagByPage(@Param(value = "page") Integer page,
                                    @Param(value = "size") Integer size);


}
