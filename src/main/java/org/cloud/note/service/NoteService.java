package org.cloud.note.service;

import org.cloud.note.VO.ServiceResult;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;


/**
 * @author wangqianlong
 * @create 2020-01-03 12:24
 */

public interface NoteService {

    /**
     * @param page
     * @param size
     * @param token
     * @return
     */
    ServiceResult<NoteDTO> getNoteByPage(Integer page, Integer size, String token);


    /**
     * 创建笔记
     *
     * @param noteDetailDTO
     * @param token
     * @return
     */
    ServiceResult<String> createNote(NoteDetailDTO noteDetailDTO, String token);


    /**
     * 根据笔记ID获取笔记
     *
     * @param noteId
     * @return
     */
    ServiceResult<NoteDetailDTO> getNoteById(Integer noteId);

    /**
     * 根据笔记ID删除笔记
     *
     * @param noteId
     * @return
     */
    ServiceResult<String> removeByNoteId(Integer noteId);

    /**
     * 修改笔记
     * @param noteDetailDTO
     * @return
     */
    ServiceResult<String> updateNote(NoteDetailDTO noteDetailDTO,String token );
}
