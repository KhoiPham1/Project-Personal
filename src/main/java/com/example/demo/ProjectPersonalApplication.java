package com.example.demo;

import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.ImageServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPersonalApplication.class, args);
    }
    @Bean
    public UserService userService () {
        return new UserServiceImpl();
    }
    @Bean
    public ImageService imageService () {
        return new ImageServiceImpl();
    }
}
