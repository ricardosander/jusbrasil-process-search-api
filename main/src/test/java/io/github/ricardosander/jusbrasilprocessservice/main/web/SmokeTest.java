package io.github.ricardosander.jusbrasilprocessservice.main.web;

import io.github.ricardosander.jusbrasilprocessservice.main.ProcessSearchApplication;
import io.github.ricardosander.jusbrasilprocessservice.web.ProcessSearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ComponentScan(basePackageClasses = ProcessSearchApplication.class)
class SmokeTest {

    @Autowired
    private ProcessSearchController processSearchController;

    @Test
    void contextLoads() {
        assertThat(processSearchController).isNotNull();
    }
}
