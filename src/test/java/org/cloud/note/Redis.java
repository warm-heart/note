package org.cloud.note;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-07-06 22:13
 */

public class Redis extends NoteApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

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

}
