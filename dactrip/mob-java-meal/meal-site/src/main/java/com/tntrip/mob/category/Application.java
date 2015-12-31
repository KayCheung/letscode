package com.tntrip.mob.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Date: 2015-05-26
 *
 * @author: fanweilian
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * 继承{@link SpringBootServletInitializer SpringBootServletInitializer}是通过war包来部署的入口
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * gradle bootRun的时候会找有main方法的类，如果有多个就必须在build.gradle里面指定是哪个类
     */
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}