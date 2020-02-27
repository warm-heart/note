package org.cloud.note.web;

import org.cloud.note.dto.*;

import org.cloud.note.entity.Notice;
import org.cloud.note.enums.ResultEnum;

import org.cloud.note.exception.UserException;
import org.cloud.note.service.AdminService;

import org.cloud.note.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * @author wangqianlong
 * @create 2020-02-08 13:15
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private NoticeService noticeService;

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

    @PostMapping("/getAllLockUser")
    public ApiResponse<UserDTO> getAllLockUser(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        ServiceResult<UserDTO> result = adminService.getAllLockUser(page, size);

        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping("/getAllLockNote")
    public ApiResponse<NoteDTO> getAllLockNote(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size) {
        ServiceResult<NoteDTO> result = adminService.getAllLockNoteByPage(page, size);

        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }
    @PostMapping(value = "/noteDetail")
    public ApiResponse<NoteDetailDTO> noteDetail(Integer noteId,
                                                 HttpServletRequest request) {
        if (StringUtils.isEmpty(String.valueOf(noteId))||noteId==null) {
            return ApiResponse.error(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<NoteDetailDTO> serviceResult = adminService.getNoteByNoteId(noteId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }

    @PostMapping("/deBlockNote")
    public ApiResponse<String> deBlockNote(@RequestParam Integer noteId) {
        if (noteId == null) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<String> result = adminService.deBlockNote(noteId);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


    @PostMapping("/lockNote")
    public ApiResponse<String> lockNote(@RequestParam Integer noteId) {
        if (noteId == null) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<String> result = adminService.lockNote(noteId);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping("/deBlockUser")
    public ApiResponse<String> deBlockUser(@RequestParam Integer userId) {
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


    @PostMapping("/createNotice")
    public ApiResponse<String> createNotice(@RequestBody @Valid Notice notice) {

        ServiceResult<String> result = noticeService.createNotice(notice);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


    @PostMapping("/updateNotice")
    public ApiResponse<String> updateNotice(@RequestBody @Valid Notice notice) {

        ServiceResult<String> result = noticeService.updateNotice(notice);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping("/removeNotice")
    public ApiResponse<String> removeNotice(@RequestParam Integer noticeId) {
        if (noticeId == null) {
            throw new UserException(ResultEnum.PARAM_ERROR);
        }

        ServiceResult<String> result = noticeService.removeNoticeById(noticeId);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }


    @PostMapping(value = "/getAllNotice")
    public ApiResponse<NoticeDTO> getAllNotice(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "5") Integer size,
                                               HttpServletRequest request) {

        ServiceResult<NoticeDTO> serviceResult = noticeService.findAllNoticeByPage(page, size);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }

    @PostMapping(value = "/noticeDetail")
    public ApiResponse<Notice> noticeDetail(Integer noticeId,
                                            HttpServletRequest request) {
        if (StringUtils.isEmpty(String.valueOf(noticeId))) {
            return ApiResponse.error(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<Notice> serviceResult = noticeService.findNoticeById(noticeId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


}
