package org.cloud.note.service;

import org.cloud.note.entity.NoteShare;

/**
 * @author wangqianlong
 * @create 2020-01-04 13:46
 */

public interface NoteShareService {


    boolean saveShare(NoteShare noteShare);

    boolean removeShareByNoteId(Integer noteId);

    boolean incrementLoveCount(Integer noteId);
}
