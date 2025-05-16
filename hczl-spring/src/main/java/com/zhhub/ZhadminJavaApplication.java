package com.zhhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = "com.src.main.resources.mapper")
public class ZhadminJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhadminJavaApplication.class, args);
    }

}
