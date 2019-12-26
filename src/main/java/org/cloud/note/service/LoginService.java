package org.cloud.note.service;

import org.cloud.note.VO.ServiceResult;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:55
 */

public interface LoginService {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
     ServiceResult login(String userName,String password);

    /** 登出
     * @return
     */
     ServiceResult logout(String token);
}
