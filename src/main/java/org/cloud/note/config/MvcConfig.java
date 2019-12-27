package org.cloud.note.config;

import org.cloud.note.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangqianlong
 * @create 2019-04-11 12:28
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //可以直接访问静态资源 比如
        // http:localhost:8080/Image/bg-1565082763608.bg.jpg
        registry.addResourceHandler("/Image/**")
                .addResourceLocations("file:E:/userIcon/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");

    }


    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(corsFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CorsFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> url = new ArrayList<>();
        url.add("/login");
        url.add("/register");
        url.add("/logout");
        url.add("/Image/**");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**").excludePathPatterns(url);

    }

}
