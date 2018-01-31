package com.ming.docker.mock;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 就打印而已
 *
 * @author CJ
 */
@Configuration
@ComponentScan("com.ming.docker.mock.bean")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//        context.refresh();

    }
}
