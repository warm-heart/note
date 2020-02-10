package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.note.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:59
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * @param userName 用户名
     * @return
     */
    User findByUserName(String userName);

    /**
     * @param userId 用户ID
     * @return
     */
    User findByUserId(Integer userId);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    Integer createUser(User user);

    /**
     * 封禁用户
     *
     * @param userId
     * @return
     */
    Integer lockUser(Integer userId);

    /**
     * 解封用户
     *
     * @param userId
     * @return
     */
    Integer deBlock(Integer userId);
}
