package com.github.dkuz.mininotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.github.dkuz.mininotes.controller"})
public class Application {
    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(Application.class, args);
    }

}