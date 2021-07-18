package io.github.ricardosander.jusbrasilprocessservice.configuration.gateways;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrieveProcessGatewayConfiguration {

    @Bean
    public RetrieveProcessGateway retrieveProcessGateway() {
        return new InMemoryRetrieveProcessGateway();
    }
}
