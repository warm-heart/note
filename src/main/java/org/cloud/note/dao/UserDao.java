package org.cloud.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    User getUserByName(String userName);

    /**
     * @param userId 用户ID
     * @return
     */
    User getUserById(Integer userId);

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
    Integer saveUser(User user);

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
    Integer deBlockUser(Integer userId);

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> listUser(@Param(value = "page") Integer page,
                        @Param(value = "size") Integer size);

    /**
     * 获取所有用户总数
     *
     * @return
     */
    Integer countUser();


    /**
     * 获取所有封禁用户
     *
     * @param page
     * @param size
     * @return
     */
    List<User> listLockUser(@Param(value = "page") Integer page,
                            @Param(value = "size") Integer size);

    /**
     * 获取封禁用户总数
     *
     * @return
     */
    Integer countLockUser();

}
