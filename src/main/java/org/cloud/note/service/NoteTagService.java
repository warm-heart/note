package org.cloud.note.service;

import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.NoteTag;

import java.util.List;


/**
 * @author wangqianlong
 * @create 2020-01-03 16:37
 */

public interface NoteTagService {
    ServiceResult<String> saveNoteTag(String noteLabel,Integer noteId);

    List<NoteTag> listNoteTagByNoteId(Integer noteId);

    Integer removeNoteTagByNoteId(Integer noteId);
}
