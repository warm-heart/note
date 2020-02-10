package org.cloud.note.web;

import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.ServiceResult;

import org.cloud.note.entity.NoteCategory;
import org.cloud.note.exception.NoteException;
import org.cloud.note.service.NoteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "/getNoteCategory")
    public ApiResponse<List<NoteCategory>> createNote(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        ServiceResult<List<NoteCategory>> serviceResult = noteCategoryService.getNoteCategoryByUserId(token);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/createCategory")
    public ApiResponse<String> createCategory(@RequestBody @Valid NoteCategory noteCategory,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        String token = request.getHeader("token");

        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get(token));
        ServiceResult<String> serviceResult = noteCategoryService.createNoteCategory(
                noteCategory.getCategoryName(), noteCategory.getCategoryDescription(), userId);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/removeCategory")
    public ApiResponse<String> removeCategory(@RequestParam(required = false) String categoryName,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(categoryName)) {
            throw new NoteException("参数不合法");
        }
        Integer userId = Integer.valueOf(stringRedisTemplate.opsForValue().get(token));
        ServiceResult<String> serviceResult = noteCategoryService.removeNoteCategory(categoryName, userId);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping(value = "/updateCategory")
    public ApiResponse<String> updateCategory(@RequestBody @Valid NoteCategory noteCategory,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {

        ServiceResult<String> serviceResult = noteCategoryService.updateNoteCategory(noteCategory);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


}
