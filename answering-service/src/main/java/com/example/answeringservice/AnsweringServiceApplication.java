package com.example.answeringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.answeringservice.config.properties")
public class AnsweringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnsweringServiceApplication.class, args);
    }

}
