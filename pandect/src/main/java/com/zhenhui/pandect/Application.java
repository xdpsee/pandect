package com.zhenhui.pandect;

import com.zhenhui.pandect.i18n.AppI18NProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static java.lang.System.setProperty;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {

        setProperty("vaadin.i18n.provider" , AppI18NProvider.class.getName());

        SpringApplication.run(Application.class, args);

    }

}
