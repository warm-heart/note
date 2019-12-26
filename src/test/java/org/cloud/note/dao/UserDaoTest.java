package org.cloud.note.dao;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.entity.User;
import org.cloud.note.utils.MD5Utils;
import org.cloud.note.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

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

    @Test
    void findByUserName() {
        System.out.println(userDao.findByUserName("wwqwqe"));
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User u = new User();
            u.setUserId(String.valueOf(i));
            userList.add(u);
        }

        redisTemplate.opsForValue().set("da", userList);
        String token = "da";
        boolean f = stringRedisTemplate.delete(token);
        System.out.println(f);
    }
}