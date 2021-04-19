package com.example.job8;

import com.example.entity.School;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class Job8Application {

    @Resource
    School school;

    @PostConstruct
    public void print(){
        System.out.println(school);
    }

    public static void main(String[] args) {

        SpringApplication.run(Job8Application.class, args);
    }

}
