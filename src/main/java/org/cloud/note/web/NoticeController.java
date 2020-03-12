package org.cloud.note.web;

import org.cloud.note.dto.*;
import org.cloud.note.entity.Notice;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author wangqianlong
 * @create 2020-01-06 17:22
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @PostMapping(value = "/getAllNotice")
    public ApiResponse<NoticeDTO> getAllNotice(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "5") Integer size,
                                               HttpServletRequest request) {

        ServiceResult<NoticeDTO> serviceResult = noticeService.listNoticeByPage(page, size);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }


    @PostMapping("/createFeedBack")
    public ApiResponse<String> createNotice(@RequestBody @Valid Notice notice) {

        ServiceResult<String> result = noticeService.createFeedBackNotice(notice);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error(result.getMessage());
    }

    @PostMapping(value = "/noticeDetail")
    public ApiResponse<Notice> noticeDetail(Integer noticeId,
                                            HttpServletRequest request) {
        if (StringUtils.isEmpty(String.valueOf(noticeId))) {
            return ApiResponse.error(ResultEnum.PARAM_ERROR);
        }
        ServiceResult<Notice> serviceResult = noticeService.getNoticeByNoticeId(noticeId);

        if (serviceResult.isSuccess()) {
            return ApiResponse.success(serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());
    }

}
