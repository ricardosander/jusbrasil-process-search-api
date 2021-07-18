package io.github.ricardosander.jusbrasilprocessservice.main.configuration.gateways;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrieveProcessGatewayConfiguration {

    @Bean(name = "mainRetrieveProcessGateway")
    public RetrieveProcessGateway retrieveProcessGateway() {
        return new InMemoryRetrieveProcessGateway();
    }
}
