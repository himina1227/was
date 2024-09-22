package com.knockknock.was;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WasApplication {

    public static void main(String[] args) {
        new WebApplicationServer(8080).start();
//        SpringApplication.run(WasApplication.class, args);
    }

}
