package org.cloud.note.service;


import org.cloud.note.dto.NoteShareDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.NoteShare;



/**
 * @author wangqianlong
 * @create 2020-01-04 13:46
 */

public interface NoteShareService {


    boolean saveShare(NoteShare noteShare);

    boolean removeShareByNoteId(Integer noteId);



    ServiceResult<NoteShareDTO> findNoteShareByPage(Integer page, Integer size);



    /**
     * 点赞
     * @param noteId
     * @return
     */
    ServiceResult<String> love(Integer noteId);
}
