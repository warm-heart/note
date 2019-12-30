package org.cloud.note.web;

import org.cloud.note.VO.ApiResponse;
import org.cloud.note.entity.User;
import org.cloud.note.exception.UnauthorizedException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author wangqianlong
 * @create 2019-12-24 7:54
 */
@RestController
@RequestMapping("/note")
public class NoteController {


    @PostMapping(value = "/createNote")
    @ResponseBody()
    public ApiResponse<User> createNote(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}