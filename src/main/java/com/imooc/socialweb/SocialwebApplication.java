package com.imooc.socialweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.imooc.socialweb.mapper")
public class SocialwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialwebApplication.class, args);
    }

}
