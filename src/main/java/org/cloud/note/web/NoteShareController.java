package org.cloud.note.web;

import org.cloud.note.VO.NoteShareVO;
import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.NoteShareDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.service.NoteShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-01-06 16:17
 */
@RestController
@RequestMapping("/noteShare")
public class NoteShareController {

    @Autowired
    private NoteShareService noteShareService;


    @PostMapping("/getNoteShare")
    public ApiResponse<NoteShareDTO> noteShare(Integer page, Integer size) {
        ServiceResult<NoteShareDTO> result = noteShareService.listNoteShareByPage(page, size);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


    @PostMapping(value = "/love")
    public ApiResponse<String> love(Integer noteId,
                                    HttpServletRequest request) {
        if (noteId == null) {
            return ApiResponse.error(ResultEnum.NOTE_NOT_FOUND);
        }
        ServiceResult<String> serviceResult = noteShareService.love(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }

}
