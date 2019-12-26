package org.cloud.note.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.cloud.note.VO.ServiceResult;
import org.cloud.note.dao.UserDao;
import org.cloud.note.entity.User;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.UserException;
import org.cloud.note.service.LoginService;
import org.cloud.note.utils.MD5Utils;
import org.cloud.note.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

/**
 * @author wangqianlong
 * @create 2019-12-25 14:56
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    TokenUtils tokenUtils;

    @Override
    public ServiceResult<String> login(String userName, String password) {
        User user = userDao.findByUserName(userName);
        if (user == null)
            throw new UserException(ResultEnum.USER_NOT_FOUND);
        if (MD5Utils.matches(password, user.getUserPassword())) {
            String token = tokenUtils.getToken();
            stringRedisTemplate.opsForValue().set(token, user.getUserId(), 7, TimeUnit.DAYS);
            return ServiceResult.success(token);
        }
        return ServiceResult.error(ResultEnum.LOGIN_FAIL.getMessage());
    }

    @Override
    public ServiceResult<String> logout(String token) {
        stringRedisTemplate.delete(token);
        return ServiceResult.success(ResultEnum.LOGOUT_SUCCESS.getMessage());
    }


}
