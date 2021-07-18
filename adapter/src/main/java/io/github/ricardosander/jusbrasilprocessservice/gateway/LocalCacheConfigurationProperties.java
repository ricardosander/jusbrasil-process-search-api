package io.github.ricardosander.jusbrasilprocessservice.gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocalCacheConfigurationProperties {
    private final int maximumSize;
    private final int ttlInMinutes;
}
