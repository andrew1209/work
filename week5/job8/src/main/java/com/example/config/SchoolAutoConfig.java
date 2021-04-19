package com.example.config;

import com.example.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SchoolProperties.class)
public class SchoolAutoConfig {

    @Autowired
    SchoolProperties schoolProperties;

    @Bean
    public School school(){
        return new School().setStudents(schoolProperties.getStudents());
    }

}
