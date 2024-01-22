package com.group1.studentsocialbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.group1.studentsocialbackend.mapper")
public class StudentSocialBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSocialBackendApplication.class, args);
    }

}
