package io.github.ricardosander.jusbrasilprocessservice.main.configuration.usecases;

import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrieveProcessUseCaseConfiguration {

    @Bean
    public RetrieveProcessUseCase retrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
        return new RetrieveProcessUseCase(retrieveProcessGateway);
    }
}
