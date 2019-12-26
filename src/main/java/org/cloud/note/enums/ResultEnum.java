package org.cloud.note.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    SUCCESS(200, "成功"),

    PARAM_ERROR(4, "参数不正确"),

    UNAUTHORIZED(401, "您好，登录已过期，请重新登录"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    USER_NOT_FOUND(26, "登录失败, 未找到用户，前往请注册"),

    LOGOUT_SUCCESS(27, "登出成功"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
