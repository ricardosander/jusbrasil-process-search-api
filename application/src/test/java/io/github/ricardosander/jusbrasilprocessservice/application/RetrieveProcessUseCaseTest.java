package io.github.ricardosander.jusbrasilprocessservice.application;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RetrieveProcessUseCaseTest {

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenNullUniqueProcessNumberingIsGiven() {

        RetrieveProcessUseCase retrieveProcessUseCase = new RetrieveProcessUseCase();

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(null));
    }
}
