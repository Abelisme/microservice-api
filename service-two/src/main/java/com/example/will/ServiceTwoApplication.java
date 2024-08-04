package com.example.will;

import com.example.will.service.NameValueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ServiceTwoApplication {

    @Bean
    CommandLineRunner generateNameValue(NameValueService nameValueService) {

        return args -> {
            nameValueService.generateUUID();
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceTwoApplication.class, args);
    }

}
