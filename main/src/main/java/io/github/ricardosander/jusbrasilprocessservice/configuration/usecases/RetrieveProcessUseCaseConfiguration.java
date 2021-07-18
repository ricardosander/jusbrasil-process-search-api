package io.github.ricardosander.jusbrasilprocessservice.configuration.usecases;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrieveProcessUseCaseConfiguration {

    @Bean
    public RetrieveProcessUseCase retrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
        return new RetrieveProcessUseCase(retrieveProcessGateway);
    }
}
