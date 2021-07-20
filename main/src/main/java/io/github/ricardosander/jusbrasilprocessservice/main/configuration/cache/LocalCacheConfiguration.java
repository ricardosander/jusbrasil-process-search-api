package io.github.ricardosander.jusbrasilprocessservice.main.configuration.cache;

import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.cache.LocalCacheConfigurationProperties;
import io.github.ricardosander.jusbrasilprocessservice.cache.LocalCacheRetrieveProcessGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LocalCacheConfiguration {

    @Bean
    @ConditionalOnExpression("${cache.local.enabled} == true and ${cache.cloud.enabled} == false")
    public RetrieveProcessGateway retrieveProcessGateway(
            LocalCacheConfigurationProperties configuration,
            @Qualifier("mainRetrieveProcessGateway") RetrieveProcessGateway retrieveProcessGateway) {
        return new LocalCacheRetrieveProcessGateway(
                configuration,
                retrieveProcessGateway
        );
    }

    @Bean
    @Primary
    @ConditionalOnExpression("${cache.local.enabled} == true and ${cache.cloud.enabled} == true")
    public RetrieveProcessGateway localCacheRetrieveProcessGateway(
            LocalCacheConfigurationProperties configuration,
            @Qualifier("cloudCacheRetrieveProcessGateway") RetrieveProcessGateway retrieveProcessGateway) {
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
