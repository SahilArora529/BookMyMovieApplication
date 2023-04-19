package com.project.bookmymovie;

import com.project.bookmymovie.services.InitService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.project")
public class BookmymovieApplication {


    public static void main(String[] args) {
        SpringApplication.run(BookmymovieApplication.class, args);
    }

    @Bean
    CommandLineRunner init (InitService initService){
        return args -> {
            initService.init();
        };
    }
}


