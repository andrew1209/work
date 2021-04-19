package com.example.config;

import com.example.entity.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
@Data
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private List<Student> students =new ArrayList<>();

}
