package io.github.ricardosander.jusbrasilprocessservice.web;

import io.github.ricardosander.jusbrasilprocessservice.web.configuration.WebConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ComponentScan(basePackageClasses = {
        WebConfiguration.class
})
class ProcessSearchContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnProcess_whenAValidProcessNumberIsGivenAndFound() throws Exception {
        this.mockMvc.perform(
                get("/{processNumber}", "07108025520188020001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clazz").exists())
                .andExpect(jsonPath("$.clazz").isString())
                .andExpect(jsonPath("$.area").exists())
                .andExpect(jsonPath("$.area").isString())
                .andExpect(jsonPath("$.subject").exists())
                .andExpect(jsonPath("$.subject").isString())
                .andExpect(jsonPath("$.distributionDate").exists())
                .andExpect(jsonPath("$.distributionDate.date").exists())
                .andExpect(jsonPath("$.distributionDate.date").isString())
                .andExpect(jsonPath("$.distributionDate.type").exists())
                .andExpect(jsonPath("$.distributionDate.type").isString())
                .andExpect(jsonPath("$.judge").exists())
                .andExpect(jsonPath("$.judge").isString())
                .andExpect(jsonPath("$.shareValue").exists())
                .andExpect(jsonPath("$.shareValue").isString())
                .andExpect(jsonPath("$.processParts").exists())
                .andExpect(jsonPath("$.processParts").isNotEmpty())
                .andExpect(jsonPath("$.processParts[*].type").exists())
                .andExpect(jsonPath("$.processParts[0].type").isString())
                .andExpect(jsonPath("$.processParts[*].subType").exists())
                .andExpect(jsonPath("$.processParts[*].part").exists())
                .andExpect(jsonPath("$.processParts[0].part").isString())
                .andExpect(jsonPath("$.movements").exists())
                .andExpect(jsonPath("$.movements").isNotEmpty())
                .andExpect(jsonPath("$.movements[*].date").exists())
                .andExpect(jsonPath("$.movements[0].date").isString())
                .andExpect(jsonPath("$.movements[*].description").exists())
                .andExpect(jsonPath("$.movements[0].description").isString());
    }

    @Test
    void shouldReturnNotFound_whenProcessIsNotFoundForGivenProcessNumber() throws Exception {
        this.mockMvc.perform(get("/08219015120188120001"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequest_whenInvalidProcessNumberIsGiven() throws Exception {
        this.mockMvc.perform(get("/5120188120001"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntity_whenNotSupportProcessNumberIsGiven() throws Exception {
        this.mockMvc.perform(get("/08219017920188900001"))
                .andExpect(status().isUnprocessableEntity());
    }

}
