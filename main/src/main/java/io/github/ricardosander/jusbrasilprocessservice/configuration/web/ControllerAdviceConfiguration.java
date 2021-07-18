package io.github.ricardosander.jusbrasilprocessservice.configuration.web;

import io.github.ricardosander.jusbrasilprocessservice.web.WebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerAdviceConfiguration {

    @Bean
    public WebExceptionHandler controllerAdvice() {
        return new WebExceptionHandler();
    }
}
