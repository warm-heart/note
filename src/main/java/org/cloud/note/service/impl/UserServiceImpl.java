package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dao.UserDao;
import org.cloud.note.entity.User;
import org.cloud.note.enums.ResultEnum;

import org.cloud.note.exception.UnauthorizedException;
import org.cloud.note.exception.UserException;
import org.cloud.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wangqianlong
 * @create 2019-12-29 11:31
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserDao userDao;

    @Override
    public User findByUserName(String userName) {
        User user = userDao.findByUserName(userName);
        if (user == null)
            throw new UserException(ResultEnum.USER_NOT_FOUND);
        return user;
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = userDao.findByUserId(userId);
        if (user == null)
            throw new UserException(ResultEnum.USER_NOT_FOUND);
        return user;
    }

    @Transactional
    @Override
    public ServiceResult<String> upUserAvatar(MultipartFile file, String token) {

        //获取上传文件名
        String fileName = file.getOriginalFilename();

        /*防止不同浏览器上传出现FileIOException 目标卷不正确*/
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

        //2.判断文件后缀名是否符合要求
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String Suffix = "png/jpg";

        if (Suffix.indexOf(fileNameSuffix) < 0) {
            return ServiceResult.error("文件类型不正确");
        }

        //3.判断文件大小是否符合要求
        int size = (int) file.getSize();//获取上传文件大小,返回字节长度1M=1024k=1048576字节 - 文件过大进入controller之前抛出异常 - 前端判断文件大小

        if (size > 1024 * 1024) {
            return ServiceResult.error("上传文件过大，请上传小于1MB大小的文件");
        }
        //4.将文件重命名，避免文件名相同覆盖文件
        String fileNamePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        fileName = fileNamePrefix + "-" + System.currentTimeMillis() + "." + fileName;//修改上传文件名
        String url = "http://localhost:8080/Image/" + fileName;


        //5.判断文件夹是否存在
        String path = "E:/userIcon";
        File targetFile = new File(path + "/" + fileName);
        if (!targetFile.getParentFile().exists()) {
            //不存在创建文件夹
            targetFile.getParentFile().mkdirs();
        }
        //6 存入数据库
        String userId = stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userId))
            throw new UnauthorizedException(ResultEnum.UNAUTHORIZED);
        User user = userDao.findByUserId(Integer.valueOf(userId));
        if (user == null) {
            throw new UserException("请重新登录");
        }
        user.setUserIcon(url);
        userDao.updateUser(user);
        try {
            //7.将上传文件写到服务器上指定的文件
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new UserException("头像上传失败，请重新上传");
        }
        return ServiceResult.success(url);
    }

}
