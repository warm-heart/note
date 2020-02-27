package org.cloud.note.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    SUCCESS(200, "成功"),

    PARAM_ERROR(4, "参数不正确"),

    UNAUTHORIZED(401, "您好，登录已过期，请重新登录"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    USER_NOT_FOUND(26, " 未找到用户，前往请注册"),

    USER_ACCOUNT_LOCK(26, "您的账户已被封禁，请联系管理员"),

    USER_ACCOUNT_LOCK_SUCCESS(26, "账户锁定成功"),

    USER_ACCOUNT_LOCK_FAIL(26, "账户锁定失败"),

    USER_ACCOUNT_DE_BLOCK_SUCCESS(26, "账户解封成功"),
    USER_ACCOUNT_DE_BLOCK_FAIL(26, "账户解封失败"),

    USER_CREATE_FAIL(26, "注册失败，请重试"),

    USER_NAME_IS_ALREADY_USED(26, "用户名已被使用"),

    USER_CREATE_SUCCESS(26, "注册成功，快去登录吧！"),

    USER_UPDATE_FAIL(26, "修改个人信息失败，请重试"),

    USER_UPDATE_SUCCESS(26, "修改个人信息成功"),

    USER_PASSWORD_UPDATE_SUCCESS(26, "密码修改成功"),

    USER_PASSWORD_UPDATE_FAIL(26, "密码修改失败"),


    YOUR_NOTE_IS_EMPTY(36, "您还没有创建笔记，赶快去创建吧"),

    CREATE_NOTE_FAIL(36, "创建笔记失败，请重新创建"),

    CREATE_NOTE_SUCCESS(36, "创建笔记成功"),

    REMOVE_NOTE_SUCCESS(36, "删除笔记成功"),

    REMOVE_NOTE_FAIL(36, "删除笔记失败"),

    UPDATE_NOTE_SUCCESS(36, "修改笔记成功"),

    UPDATE_NOTE_FAIL(36, "修改笔记失败"),

    NOTE_NOT_FOUND(36, "未找到笔记"),

    NOTE_LOCK_SUCCESS(36, "笔记锁定成功"),

    NOTE_LOCK_FAIL(36, "笔记锁定失败"),

    NOTE_DE_BLOCK_SUCCESS(36, "笔记解封成功"),

    NOTE_DE_BLOCK_FAIL(36, "笔记解封失败"),


    NOTE_CATEGORY_IS_EMPTY(46, "笔记分类为空，请创建笔记分类"),

    NOTE_CATEGORY_NOT_FOUND(46, "未找到此笔记分类，请创建笔记分类"),

    CREATE_NOTE_CATEGORY_SUCCESS(46, "创建笔记分类成功"),

    CREATE_NOTE_CATEGORY_FAIL(46, "创建笔记分类失败，请重新创建"),

    NOTE_CATEGORY_NOT_LESS_ONE(46, "您只有一个笔记分类，不能删除了哦！"),

    NOTE_CATEGORY_REMOVE_SUCCESS(46, "笔记分类删除成功"),

    NOTE_CATEGORY_REMOVE_FAIL(46, "笔记分类删除失败"),

    NOTE_CATEGORY_UPDATE_SUCCESS(46, "修改笔记分类成功"),

    NOTE_CATEGORY_UPDATE_FAIL(46, "修改笔记分类成功"),


    CREATE_NOTE_TAG_FAIL(56, "标签创建失败，请重新创建"),

    CREATE_NOTE_TAG_SUCCESS(56, "标签创建成功"),


    LOGOUT_SUCCESS(27, "登出成功"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
