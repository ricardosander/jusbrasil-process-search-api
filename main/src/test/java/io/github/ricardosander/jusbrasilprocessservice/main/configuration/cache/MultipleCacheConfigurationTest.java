package io.github.ricardosander.jusbrasilprocessservice.main.configuration.cache;

import io.github.ricardosander.jusbrasilprocessservice.cache.LocalCacheRetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.main.ProcessSearchApplication;
import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(properties = { "cache.local.enabled=true", "cache.cloud.enabled=true"})
@ComponentScan(basePackageClasses = ProcessSearchApplication.class)
class MultipleCacheConfigurationTest {

    @Autowired
    private RetrieveProcessGateway processGateway;

    @Test
    void shouldLoadLocalCacheRetrieveProcessGateway_whenLocalCacheConfigIsEnabled() {
        assertThat(processGateway)
                .isNotNull()
                .isInstanceOf(LocalCacheRetrieveProcessGateway.class);
    }
}
