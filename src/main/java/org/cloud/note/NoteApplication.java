package org.cloud.note;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.nio.channels.Channel;

@Slf4j
@SpringBootApplication
public class NoteApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NoteApplication.class, args);
        SpringApplication springApplication = new SpringApplication(NoteApplication.class);
        springApplication.addListeners(new ApplicationListener<ApplicationContextEvent>() {
            @Override
            public void onApplicationEvent(ApplicationContextEvent event) {
                log.info("{}", event.getApplicationContext()
                        .getBeanDefinitionCount());
                log.info("{}", event.getSource());
            }
        });
        springApplication.run();

    }

}
