package org.cloud.note.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqianlong
 * @create 2019-07-29 16:28
 */
@Component
@Slf4j
public class TokenUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成唯一的Token
     *
     * @return
     */
    public synchronized String getToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            log.info("生成Token失败");
        }
        return null;
    }

    public boolean verify(String token) {
        if (StringUtils.isEmpty(token))
            return false;
        String userId = stringRedisTemplate.opsForValue().get(token);
        if (!StringUtils.isEmpty(userId)) {
            stringRedisTemplate.expire(token, 7, TimeUnit.DAYS);
            return true;
        }
        return false;
    }
}
