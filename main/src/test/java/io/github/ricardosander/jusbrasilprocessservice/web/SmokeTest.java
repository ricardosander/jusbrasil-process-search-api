package io.github.ricardosander.jusbrasilprocessservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ComponentScan(basePackages = "io.github.ricardosander.jusbrasilprocessservice.configuration")
class SmokeTest {

    @Autowired
    private ProcessSearchController processSearchController;

    @Test
    void contextLoads() {
        assertThat(processSearchController).isNotNull();
    }
}
