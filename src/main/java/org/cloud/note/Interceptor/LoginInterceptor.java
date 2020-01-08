package org.cloud.note.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.UnauthorizedException;
import org.cloud.note.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangqianlong
 * @create 2019-04-02 16:59
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (tokenUtils.verify(token)) {
            return true;
        } else {
            log.error(  "token不合法,token: {}", token);
            throw new UnauthorizedException(ResultEnum.UNAUTHORIZED);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("执行过滤器postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("执行过滤器afterCompletion");
    }
}
