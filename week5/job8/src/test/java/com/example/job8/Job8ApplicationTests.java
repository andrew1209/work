package com.example.job8;

import com.example.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Job8ApplicationTests {

    @Resource
    School school;

    @Test
    void contextLoads() {

        System.out.println(school);
    }

}
