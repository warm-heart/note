package org.cloud.note.web;

import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.UserDTO;

import org.cloud.note.enums.ResultEnum;

import org.cloud.note.exception.UserException;
import org.cloud.note.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangqianlong
 * @create 2020-02-08 13:15
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/getAllUser")
    public ApiResponse<UserDTO> getAllUser(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        ServiceResult<UserDTO> result = adminService.getAllUser(page, size);

        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping("/getAllNote")
    public ApiResponse<NoteDTO> getAllNote(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        ServiceResult<NoteDTO> result = adminService.getAllNoteByPage(page, size);

        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping("/deBlockUser")
    public ApiResponse<String> deBlock(@RequestParam Integer userId) {
        if (userId == null) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<String> result = adminService.deBlockUser(userId);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


    @PostMapping("/lockUser")
    public ApiResponse<String> lockUser(@RequestParam Integer userId) {
        if (userId == null) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<String> result = adminService.lockUser(userId);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


}
