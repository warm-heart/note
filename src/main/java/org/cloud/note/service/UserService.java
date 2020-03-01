package org.cloud.note.service;

import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.UserDTO;
import org.cloud.note.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2019-12-29 11:31
 */

public interface UserService {


    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return
     */
    User getUserByName(String userName);

    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户Id
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 用户修改头像
     *
     * @param file  头像
     * @param token token
     * @return
     */
    ServiceResult upUserAvatar(MultipartFile file, String token);

    /**
     * 创建用户
     *
     * @param user 用户
     * @return
     */
    ServiceResult<String> saveUser(User user);


    /**
     * 修改个人信息
     *
     * @param user 用户
     * @return
     */
    ServiceResult<String> updateUser(User user);

    /**
     * 找回密码
     *
     * @param password
     * @param userName
     * @return
     */
    ServiceResult<String> findPassword(String password, String userName);

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param token
     * @return
     */
    ServiceResult<String> updatePassword(String oldPassword, String newPassword, String token);

    /**
     * 查找所有用户
     *
     * @param page
     * @param size
     * @return
     */
    ServiceResult<UserDTO> listUser(Integer page, Integer size);

    /**
     * 查找所有被封禁的用户
     *
     * @param page
     * @param size
     * @return
     */
    ServiceResult<UserDTO> listLockUser(Integer page, Integer size);

    /**
     * 解封用户
     *
     * @param userId
     * @return
     */
    ServiceResult<String> deBlockUser(Integer userId);

    /**
     * 封禁用户
     *
     * @param userId
     * @return
     */
    ServiceResult<String> lockUser(Integer userId);


    ServiceResult<List<Integer>> countNoteAndCategory(String token);
}
