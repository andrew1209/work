package com.andrew.mysqltest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.andrew.mysqltest.mapper")
public class MysqltestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqltestApplication.class, args);
    }

}
