package org.cloud.note.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangqianlong
 * @create 2020-02-27 11:04
 */
@Component
@Slf4j
public class NoteInit implements CommandLineRunner {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化加载数据");

    }
}
