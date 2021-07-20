package io.github.ricardosander.jusbrasilprocessservice.web.configuration;

import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public RetrieveProcessUseCase retrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
        return new RetrieveProcessUseCase(retrieveProcessGateway);
    }

    @Bean
    public RetrieveProcessGateway retrieveProcessGateway() {
        return new InMemoryRetrieveProcessGateway();
    }
}
