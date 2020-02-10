package org.cloud.note;

import org.cloud.note.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NoteApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NoteApplication.class, args);

    }

}
