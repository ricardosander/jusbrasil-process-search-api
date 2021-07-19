package io.github.ricardosander.jusbrasilprocessservice.main.configuration.cache;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.cache.LocalCacheConfigurationProperties;
import io.github.ricardosander.jusbrasilprocessservice.cache.LocalCacheRetrieveProcessGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LocalCacheConfiguration {

    @Bean
    @Primary
    @ConditionalOnProperty(
            value = "cache.local.enabled",
            havingValue = "true"
    )
    public RetrieveProcessGateway retrieveProcessGateway(
            LocalCacheConfigurationProperties configuration,
            @Qualifier("mainRetrieveProcessGateway") RetrieveProcessGateway retrieveProcessGateway) {
        return new LocalCacheRetrieveProcessGateway(
                configuration,
                retrieveProcessGateway
        );
    }

    @Bean
    public LocalCacheConfigurationProperties localCacheConfigurationProperties(
            @Value("${cache.local.maximumSize:100}") int maximumSize,
            @Value("${cache.local.ttlInMinutes:60}") int ttlInMinutes
    ) {
        return new LocalCacheConfigurationProperties(maximumSize, ttlInMinutes);
    }
}
