package com.example.job2.config;

import com.example.job2.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean(name = "student")
    public Student student(){
        Student student = new Student();
        student.setId("111");
        student.setName("name");
        return student;
    }
}
