package org.cloud.note.service;


import org.cloud.note.dto.NoteAnalysis;
import org.cloud.note.dto.NoteShareDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.NoteShare;

import java.util.List;


/**
 * @author wangqianlong
 * @create 2020-01-04 13:46
 */

public interface NoteShareService {


    boolean saveNoteShare(NoteShare noteShare);

    boolean removeNoteShareByNoteId(Integer noteId);



    ServiceResult<NoteShareDTO> listNoteShareByPage(Integer page, Integer size);



    /**
     * 点赞
     * @param noteId
     * @return
     */
    ServiceResult<String> love(Integer noteId);

    List<NoteAnalysis> noteShareAnalysis();
}
