package com.obss.movietracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.obss.movietracker.model"} )
@EnableJpaRepositories(basePackages = {"com.obss.movietracker.repository"})
public class MovieTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieTrackerApplication.class, args);
    }
}
