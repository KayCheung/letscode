package com.tntrip.mob.askq;

import com.tntrip.mob.askq.biz.pojo.Pojo;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        //MybatisAutoConfiguration.class,
        //DataSourceAutoConfiguration.class
})
public class HelloSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class, args);
    }

    @Bean
    public Pojo pojoInSpringBootApplication() {
        return new Pojo();
    }
}
