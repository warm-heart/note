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
     * @param userName  用户名
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
     * @param user
     * @return
     */
    Integer updateUser(User user);
}
