package org.cloud.note.web;

import org.cloud.note.VO.ApiResponse;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.NoteDetailDTO;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


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
    @ResponseBody()
    public ApiResponse<String> createNote(@RequestBody @Valid NoteDetailDTO noteDetailDTO, HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("token");
        ServiceResult<String> serviceResult = noteService.createNote(noteDetailDTO, token);
        if (serviceResult.isSuccess())
            return ApiResponse.success(serviceResult.getResult());
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/getMyNote")
    @ResponseBody()
    public ApiResponse<NoteDTO> getMyNote(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "5") Integer size,
                                          HttpServletRequest request) {

        String token = request.getHeader("token");
        ServiceResult<NoteDTO> serviceResult = noteService.getNoteByPage(page, size, token);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/getNoteByNoteId")
    @ResponseBody()
    public ApiResponse<NoteDetailDTO> getNoteById(Integer noteId,
                                                  HttpServletRequest request) {
        if (noteId == null) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }
        ServiceResult<NoteDetailDTO> serviceResult = noteService.getNoteById(noteId);

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
    public ApiResponse<String> updateNote(@RequestBody NoteDetailDTO noteDetailDTO,
                                          HttpServletRequest request) {
        String token = request.getHeader("token");
        ServiceResult<String> serviceResult = noteService.updateNote(noteDetailDTO, token);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }
}
