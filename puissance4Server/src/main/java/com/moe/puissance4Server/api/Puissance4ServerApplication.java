package com.moe.puissance4Server.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.moe.puissance4Server", "com.moe.puissance4back"})
public class Puissance4ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Puissance4ServerApplication.class, args);
    }
}
