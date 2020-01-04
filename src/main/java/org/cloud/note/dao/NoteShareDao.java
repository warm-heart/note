package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.note.entity.NoteShare;
import org.springframework.stereotype.Repository;

/**
 * @author wangqianlong
 * @create 2020-01-03 12:26
 */
@Mapper
@Repository
public interface NoteShareDao {

    Integer saveShare(NoteShare noteShare);

    Integer removeShareByNoteId(Integer noteId);

    Integer incrementLoveCount(Integer noteId);
}
