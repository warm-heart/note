package org.cloud.note.web;

import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.ServiceResult;

import org.cloud.note.entity.NoteCategory;
import org.cloud.note.service.NoteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wangqianlong
 * @create 2019-12-24 17:41
 */
@RestController
@RequestMapping("/noteCategory")
public class NoteCategoryController {
    @Autowired
    private NoteCategoryService noteCategoryService;

    @PostMapping(value = "/getNoteCategory")
    public ApiResponse<List<NoteCategory>> createNote(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        ServiceResult<List<NoteCategory>> serviceResult = noteCategoryService.getNoteCategoryByUserId(token);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }

}
