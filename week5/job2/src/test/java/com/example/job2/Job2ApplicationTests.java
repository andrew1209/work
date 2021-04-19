package com.example.job2;

import com.example.job2.bean.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Job2ApplicationTests {

    @Resource(name = "student")
    Student student;

    @Autowired
    Student student2;

    /**
     * 通过配置文件的方式配置
     */
    @Test
    void contextLoads() {
        System.out.println(student);
        System.out.println(student2);
    }

}
