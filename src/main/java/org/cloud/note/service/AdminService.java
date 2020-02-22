package org.cloud.note.service;

import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.UserDTO;




/**
 * @author wangqianlong
 * @create 2020-02-13 14:29
 */

public interface AdminService {
    ServiceResult<UserDTO> getAllUser(Integer page, Integer size);

    ServiceResult<NoteDTO> getAllNoteByPage(Integer page, Integer size);

    ServiceResult<NoteDetailDTO> getNoteByNoteId(Integer noteId);


    ServiceResult<String> deBlockNote(Integer noteId);


    ServiceResult<String> lockNote(Integer noteId);


    ServiceResult<String> deBlockUser(Integer userId);


    ServiceResult<String> lockUser(Integer userId);
}
