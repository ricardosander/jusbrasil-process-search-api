package io.github.ricardosander.jusbrasilprocessservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = ProcessSearchApplication.class)
public class ProcessSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessSearchApplication.class, args);
    }
}
