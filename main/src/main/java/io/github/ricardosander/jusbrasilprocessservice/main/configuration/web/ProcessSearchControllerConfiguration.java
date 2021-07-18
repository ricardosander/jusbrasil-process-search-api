package io.github.ricardosander.jusbrasilprocessservice.main.configuration.web;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessUseCase;
import io.github.ricardosander.jusbrasilprocessservice.web.ProcessSearchController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessSearchControllerConfiguration {

    @Bean
    public ProcessSearchController processSearchController(RetrieveProcessUseCase retrieveProcessUseCase) {
        return new ProcessSearchController(retrieveProcessUseCase);
    }

}
