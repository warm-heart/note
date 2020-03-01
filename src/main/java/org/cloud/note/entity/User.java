package org.cloud.note.entity;

import lombok.Data;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2019-12-02 18:41
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 2705003623444254647L;
    private Integer userId;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "请输入昵称")
    private String nickName;
    private String userPassword;
    private String userIcon;

    @NotEmpty(message = "地址不能为空")
    private String userAddress;

    // @Pattern(regexp = "", message = "请输入正确的邮箱")
    @Email(message = "请输入正确的邮箱")
    @NotEmpty(message = "邮箱不能为空")
    private String userEmail;

    @Min(value = 0, message = "性别参数错误")
    @Max(value = 1, message = "性别参数错误")
    @NotNull(message = "性别不能为空")
    private Integer userSex;

    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",
            message = "请输入正确手机号")
    @NotEmpty(message = "电话不能为空")
    private String userPhone;

    @Past(message = "请输入正确的生日")
    @NotNull(message = "生日不能为空")
    private Date birthday;
    private Integer roleId;
    private Integer userStatus;
    private Date updateTime;
    private Date createTime;
}
