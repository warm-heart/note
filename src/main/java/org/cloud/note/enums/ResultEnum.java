package org.cloud.note.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    SUCCESS(200, "成功"),

    PARAM_ERROR(4, "参数不正确"),

    UNAUTHORIZED(401, "您好，登录已过期，请重新登录"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    USER_NOT_FOUND(26, "登录失败, 未找到用户，前往请注册"),

    USER_ACCOUNT_LOCK(28, "您的账户已被封禁，请联系管理员"),


    YOUR_NOTE_IS_EMPTY(29, "您还没有创建笔记，赶快去创建吧"),

    CREATE_NOTE_FAIL(32, "创建笔记失败，请重新创建"),

    CREATE_NOTE_SUCCESS(32, "创建笔记成功"),

    REMOVE_NOTE_SUCCESS(32, "删除笔记成功"),

    REMOVE_NOTE_FAIL(32, "删除笔记失败"),

    UPDATE_NOTE_SUCCESS(32, "修改笔记成功"),

    UPDATE_NOTE_FAIL(32, "修改笔记失败"),

    NOTE_NOT_FOUND(35, "未找到笔记"),



    NOTE_CATEGORY_IS_EMPTY(36, "笔记分类为空，请创建笔记分类"),

    NOTE_CATEGORY_NOT_FOUND(36, "未找到此笔记分类，请创建笔记分类"),

    CREATE_NOTE_CATEGORY_SUCCESS(30, "创建笔记分类成功"),

    CREATE_NOTE_CATEGORY_FAIL(30, "创建笔记分类失败，请重新创建"),

    NOTE_CATEGORY_NOT_LESS_ONE(31, "不能删除，至少要有一个笔记分类"),


    CREATE_NOTE_TAG_FAIL(33, "标签创建失败，请重新创建"),

    CREATE_NOTE_TAG_SUCCESS(34, "标签创建成功"),


    LOGOUT_SUCCESS(27, "登出成功"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
