package com.zhenhui.pandect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}
