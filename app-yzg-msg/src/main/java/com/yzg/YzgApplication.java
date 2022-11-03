package com.yzg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author walle
 */
@EnableScheduling
@SpringBootApplication
public class YzgApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzgApplication.class, args);
    }

}
