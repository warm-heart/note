package org.cloud.note.exception;


import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangqianlong
 * @create 2018-09-14 20:00
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse UnauthorizedException(Exception e, HttpServletRequest request) {
        log.error("未认证 , uri: {} , caused by:{} ", request.getRequestURI(), e);
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ApiResponse UserException(Exception e, HttpServletRequest request) {
        log.error("用户相关异常 , uri: {} , caused by{}: ", request.getRequestURI(), e);
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(NoteException.class)
    @ResponseBody
    public ApiResponse NoteException(Exception e, HttpServletRequest request) {
        log.error("笔记相关异常 , uri: {} , caused by{}: ", request.getRequestURI(), e.getMessage());
        return ApiResponse.error(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse Exception(Exception e, HttpServletRequest request) {
        log.error("服务器异常 , uri: {} , caused by :{} ", request.getRequestURI(), e);

        return ApiResponse.error("服务器错误，请稍后重试");
    }

    //处理@RequestBody注解异常 处理@RequestParam的是ConstraintViolationException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("参数校验错误 , uri: {} ,  ", request.getRequestURI());
        return ApiResponse.error(fieldError.getDefaultMessage());
    }


}
