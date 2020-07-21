package org.cloud.note.config;

import org.cloud.note.service.NoteService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wangqianlong
 * @create 2020-07-09 21:01
 */
@Component
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof NoteService)
//            System.err.println("是NoteService的bean");
//        return bean;
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NoteService)
            return null;
        return bean;
    }
}
