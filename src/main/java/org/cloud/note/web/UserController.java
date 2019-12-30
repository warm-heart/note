package org.cloud.note.web;

import org.cloud.note.VO.ApiResponse;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangqianlong
 * @create 2019-12-24 17:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/updateUserIcon")
    public ApiResponse<String> file(@RequestParam(name = "file", required = false)
                                            MultipartFile file, HttpServletRequest request) {

        //1.判断文件是否为空(是否上传文件 / 文件内容是否为空)
        if (file == null || file.isEmpty()) {
            return ApiResponse.error("上传头像不可以为空");
        }
        String token = request.getHeader("token");
        ServiceResult serviceResult = userService.upUserAvatar(file, token);
        if (serviceResult.isSuccess())
            return ApiResponse.success((String) serviceResult.getResult());
        return ApiResponse.error(serviceResult.getMessage());
    }
}
