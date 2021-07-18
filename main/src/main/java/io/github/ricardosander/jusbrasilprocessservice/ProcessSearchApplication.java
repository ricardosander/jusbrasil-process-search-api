package io.github.ricardosander.jusbrasilprocessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.github.ricardosander.jusbrasilprocessservice.configuration")
public class ProcessSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessSearchApplication.class, args);
    }
}
