package org.sit707.ontrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@Configuration
public class OnTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnTrackApplication.class, args);
    }

}
