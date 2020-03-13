package org.cloud.note.service.impl;

import org.cloud.note.NoteApplicationTests;
import org.cloud.note.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends NoteApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void findByUserName() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void upUserAvatar() {
    }

    @Test
    void createUser() {
        User user = new User();
        user.setUserPassword("123");
        user.setUserName("wang");
        user.setUserPhone("1234567890");
        user.setUserAddress("安徽省");
        user.setNickName("暖心人");
        user.setUserEmail("178625120@qq.com");
        user.setUserIcon("http:localodad");

        userService.saveUser(user);
    }


}