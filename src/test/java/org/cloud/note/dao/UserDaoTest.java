package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.entity.User;
import org.cloud.note.utils.MD5Utils;
import org.cloud.note.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest extends NoteApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenUtils tokenUtils;

    @Test
    void findByUserName() {
        System.out.println(userDao.getUserByName("cooper"));
        System.out.println(MD5Utils.encode("123456"));
    }


    @Test
    void updateUser() {
        User user = new User();
        user.setUserIcon("da");
        user.setNickName("da");
        user.setUserAddress("da");
        user.setUserEmail("da");
        user.setUserPassword("da");
        user.setUserPhone("da");
        user.setUserId(2);
        user.setUserName("da");
        Integer integer = userDao.updateUser(user);
        System.out.println("条数" + integer);
    }

    @Test

    void findByUserId() {
        System.out.println(userDao.getUserById(1));

        System.out.println(userDao.getUserById(1));
    }


    @Test
    void lockUser() {
        Integer res = userDao.lockUser(2);
        System.out.println(res);
    }


    @Test
    void deBlock() {
        Integer res = userDao.deBlockUser(2);
        System.out.println(res);
    }
}