package com.example.foodapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodapiApplication {
    private static final Logger LOG = LoggerFactory.getLogger(FoodapiApplication.class);
    public static void main(String[] args) {
        LOG.info("STARTING");
        SpringApplication.run(FoodapiApplication.class, args);
        LOG.info("FINISHED");
    }

}
