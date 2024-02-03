package com.group1.studentsocialbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cookie")
public class CookieConfig {
    private String name;

    private String path;
}
