package org.cloud.note.exception;


import lombok.extern.slf4j.Slf4j;
import org.cloud.note.VO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

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
    public ApiResponse UnauthorizedException(Exception e) {
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(NoteException.class)
    @ResponseBody
    public ApiResponse NoteException(Exception e) {
        return ApiResponse.error(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse Exception(Exception e) {

        return ApiResponse.error(e.getMessage());
    }
}
