package org.cloud.note.config;

import org.cloud.note.mybatisinterceptor.PageInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangqianlong
 * @create 2020-08-02 13:40
 */
@Configuration
public class MybatisConfig {


    @Bean
    public ConfigurationCustomizer configurationCustomizer (){
        return e->{e.addInterceptor(new PageInterceptor());};
    }
}
