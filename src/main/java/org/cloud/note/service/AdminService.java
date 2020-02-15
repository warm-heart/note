package org.cloud.note.service;

import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.UserDTO;
import org.cloud.note.entity.User;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-02-13 14:29
 */

public interface AdminService {
    ServiceResult<UserDTO> getAllUser(Integer page, Integer size);

    ServiceResult<NoteDTO> getAllNoteByPage(Integer page, Integer size);
}
