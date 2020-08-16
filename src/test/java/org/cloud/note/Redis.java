package org.cloud.note;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqianlong
 * @create 2020-07-06 22:13
 */

public class Redis extends NoteApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void redisLua() {
//        RedisScript script = RedisScript
//                .of(new ClassPathResource("redis.lua")) ;

        RedisScript script = RedisScript
                .of("return redis.call('get',KEYS[1])");
        List<String> keys = Arrays.asList("hello");
        String res = (String) stringRedisTemplate.execute(script, keys, "world");
        System.out.println(res);
    }

    @Test
    public  void setIfAbsent(){
        boolean res = stringRedisTemplate.opsForValue().setIfAbsent("name", "va", 1L, TimeUnit.HOURS);
        System.out.println(res);
    }
    @Test
    public  void expire (){
        redisTemplate.expire("name",1L,TimeUnit.HOURS);
    }

}
