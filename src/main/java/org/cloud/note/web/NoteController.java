package org.cloud.note.web;

import org.cloud.note.VO.NoteVO;
import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;
import org.cloud.note.entity.Note;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


/**
 * @author wangqianlong
 * @create 2019-12-24 7:54
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;


    @PostMapping(value = "/createNote")
    public ApiResponse<String> createNote(@RequestBody @Valid NoteVO noteVO, HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("token");
        ServiceResult<String> serviceResult = noteService.createNote(noteVO, token);
        if (serviceResult.isSuccess())
            return ApiResponse.success(serviceResult.getResult());
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/myNote")
    public ApiResponse<NoteDTO> MyNote(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "5") Integer size,
                                       HttpServletRequest request) {

        String token = request.getHeader("token");
        ServiceResult<NoteDTO> serviceResult = noteService.getNoteByPage(page, size, token);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/noteDetail")
    public ApiResponse<NoteDetailDTO> noteDetail(Integer noteId,
                                                 HttpServletRequest request) {
        if (StringUtils.isEmpty(String.valueOf(noteId))) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }
        ServiceResult<NoteDetailDTO> serviceResult = noteService.getNoteByNoteId(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/removeNote")
    @ResponseBody()
    public ApiResponse<String> removeNote(Integer noteId,
                                          HttpServletRequest request) {
        if (noteId == null) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }

        ServiceResult<String> serviceResult = noteService.removeByNoteId(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/updateNote")
    @ResponseBody()
    public ApiResponse<String> updateNote(@RequestBody NoteVO noteVO,
                                          HttpServletRequest request) {
        String token = request.getHeader("token");
        ServiceResult<String> serviceResult = noteService.updateNote(noteVO, token);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/findNoteByCategory")
    public ApiResponse<List<Note>> findNoteByCategory(Integer categoryId,
                                                      HttpServletRequest request) {


        String token = request.getHeader("token");
        ServiceResult<List<Note>> serviceResult = noteService.getNoteByCategoryIdAndUserId(categoryId, token);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/shareNote")

    public ApiResponse<String> shareNote(Integer noteId,
                                         HttpServletRequest request) {
        if (noteId == null) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }
        ServiceResult<String> serviceResult = noteService.shareNote(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/cancelShareNote")
    public ApiResponse<String> cancelShareNote(Integer noteId,
                                               HttpServletRequest request) {
        if (noteId == null) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }
        ServiceResult<String> serviceResult = noteService.cancelShareNote(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


}
