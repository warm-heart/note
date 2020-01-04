package org.cloud.note.service;

import org.cloud.note.dto.ServiceResult;
import org.cloud.note.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangqianlong
 * @create 2019-12-29 11:31
 */

public interface UserService {


    /**
     * @param userName 用户名
     * @return
     */
    User findByUserName(String userName);

    /**
     * @param userId 用户Id
     * @return
     */
    User findByUserId(Integer userId);

    /**
     * 修改用户头像
     * @param file 头像
     * @param token token
     * @return
     */
    ServiceResult upUserAvatar(MultipartFile file, String token);
}
