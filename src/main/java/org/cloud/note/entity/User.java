package org.cloud.note.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String nickName;
    @NotEmpty(message = "密码不能为空")
    private String userPassword;
    private String userIcon;
    private String userAddress;
    private String userEmail;
    private String userPhone;
    private Date updateTime;
}
