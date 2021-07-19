package io.github.ricardosander.jusbrasilprocessservice.main.configuration.cache;

import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.Delay;
import io.lettuce.core.resource.DirContextDnsResolver;
import io.lettuce.core.resource.DnsResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class CacheManagerConfiguration {

    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory,
            RedisCacheConfiguration redisCacheConfiguration
    ) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(
            RedisStandaloneConfiguration redisStandaloneConfiguration,
            LettuceClientConfiguration clientConfiguration
    ) {
        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(@Value("${cache.cloud.ttlInMinutes:60}") int ttlInMinutes) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(ttlInMinutes))
                .disableCachingNullValues();
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(
            @Value("${cache.cloud.host:localhost}") String host,
            @Value("${cache.cloud.port:6379}") int port
    ) {
        return new RedisStandaloneConfiguration(host, port);
    }

    @Bean
    public LettuceClientConfiguration clientConfiguration(ClientResources clientResources) {
        return LettuceClientConfiguration.builder()
                .clientResources(clientResources)
                .commandTimeout(Duration.ofSeconds(3))
                .build();
    }

    @Bean
    public ClientResources clientResources(
            DnsResolver dnsResolver,
            @Value("${cache.cloud.reconnectionDelayInSeconds:3}") long reconnectionDelayInSeconds
    ) {
        Delay reconnectionDelay = convertToDelay(reconnectionDelayInSeconds);

        return DefaultClientResources.builder()
                .dnsResolver(dnsResolver)
                .reconnectDelay(reconnectionDelay)
                .build();
    }

    @Bean
    public DnsResolver dnsResolver() {
        return new DirContextDnsResolver();
    }

    private Delay convertToDelay(long reconnectionDelayInSeconds) {
        return Delay.constant(Duration.ofSeconds(reconnectionDelayInSeconds));
    }

}
