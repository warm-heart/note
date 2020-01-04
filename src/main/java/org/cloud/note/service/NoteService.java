package org.cloud.note.service;

import org.cloud.note.VO.NoteVO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;
import org.cloud.note.entity.Note;

import java.util.List;


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
     * @param
     * @param token
     * @return
     */
    ServiceResult<String> createNote(NoteVO noteVO, String token);


    /**
     * 根据笔记ID获取笔记
     *
     * @param noteId
     * @return
     */
    ServiceResult<NoteDetailDTO> getNoteByNoteId(Integer noteId);

    /**
     * 根据笔记ID删除笔记
     *
     * @param noteId
     * @return
     */
    ServiceResult<String> removeByNoteId(Integer noteId);

    /**
     * 修改笔记
     *
     * @param
     * @return
     */
    ServiceResult<String> updateNote(NoteVO noteVO, String token);

    /**
     * 获取笔记本中的笔记
     *
     * @param categoryId
     * @param token
     * @return
     */
    ServiceResult<List<Note>> getByCategoryIdAndUserId(Integer categoryId, String token);

    /**
     * 分享笔记
     *
     * @param noteId
     * @return
     */
    ServiceResult<String> shareNote(Integer noteId);


    /** 取消分享
     * @param noteId
     * @return
     */
    ServiceResult<String> cancelShareNote(Integer noteId);


    /**
     * 点赞
     * @param noteId
     * @return
     */
    ServiceResult<String> love(Integer noteId);

}
