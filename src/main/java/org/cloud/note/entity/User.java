package org.cloud.note.entity;

import lombok.Data;

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
    private String userName;
    private String nickName;
    private String userPassword;
    private String userIcon;
    private String userAddress;
    private String userEmail;
    private String userPhone;
    private Date updateTime;
}
