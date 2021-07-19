package io.github.ricardosander.jusbrasilprocessservice.main.configuration.cache;

import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.cache.CloudCacheRetrieveProcessGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudCacheConfiguration {

    @Bean
    @ConditionalOnExpression("${cache.cloud.enabled} == true and ${cache.local.enabled} == false")
    public RetrieveProcessGateway retrieveProcessGateway(
            @Qualifier("mainRetrieveProcessGateway") RetrieveProcessGateway retrieveProcessGateway,
            CacheManager cacheManager
    ) {
        return new CloudCacheRetrieveProcessGateway(retrieveProcessGateway, cacheManager);
    }

    @Bean("cloudCacheRetrieveProcessGateway")
    @ConditionalOnExpression("${cache.cloud.enabled} == true and ${cache.local.enabled} == true")
    public RetrieveProcessGateway cloudCacheRetrieveProcessGateway(
            @Qualifier("mainRetrieveProcessGateway") RetrieveProcessGateway retrieveProcessGateway,
            CacheManager cacheManager
    ) {
        return new CloudCacheRetrieveProcessGateway(retrieveProcessGateway, cacheManager);
    }
}
