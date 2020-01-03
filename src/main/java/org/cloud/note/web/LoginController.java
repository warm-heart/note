package org.cloud.note.web;


import org.cloud.note.VO.ApiResponse;
import org.cloud.note.VO.ServiceResult;

import org.cloud.note.entity.User;
import org.cloud.note.service.LoginService;
import org.cloud.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.HtmlUtils;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wangqianlong
 * @create 2019-12-25 10:45
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ApiResponse<List<String>> login(@RequestBody @Valid User user1) {
        String userName = user1.getUserName();
        String password = user1.getUserPassword();
        User user = userService.findByUserName(userName);
        ServiceResult serviceResult = loginService.login(userName, password);

        if (serviceResult.isSuccess()) {
            List<String> res = new ArrayList<>();
            res.add((String) serviceResult.getResult());
            res.add(user.getUserIcon());
            return ApiResponse.success(res);
        }
        return ApiResponse.error(serviceResult.getMessage());

    }


    @PostMapping("/register")
    public ApiResponse register(@RequestBody @Valid User user1) {
        //todo 注册建立默认笔记本
        return null;
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(String token) {
        //解决前端传送的数据中带有特殊字符（+ 会转化为空格）
        String Token = HtmlUtils.htmlEscape(token);
        if (StringUtils.isEmpty(Token)) {
            return ApiResponse.error("你还未登录");
        }
        ServiceResult serviceResult = loginService.logout(Token);
        if (serviceResult.isSuccess()) {
            return ApiResponse.success((String) serviceResult.getResult());
        }
        return ApiResponse.error(serviceResult.getMessage());

    }


}
