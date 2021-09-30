package com.coden.kantools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.coden.kantools.controller", "com.coden.kantools.service", "com.coden.kantools.model", "com.coden.kantools.util"})
@MapperScan("com.coden.kantools.mapper")
public class KantoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KantoolsApplication.class, args);
    }

}
