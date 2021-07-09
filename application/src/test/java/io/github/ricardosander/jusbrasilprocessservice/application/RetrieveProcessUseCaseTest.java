package io.github.ricardosander.jusbrasilprocessservice.application;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RetrieveProcessUseCaseTest {

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenNullUniqueProcessNumberingIsGiven() {

        RetrieveProcessUseCase retrieveProcessUseCase = new RetrieveProcessUseCase();

        String uniqueProcessNumbering = null;

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenEmptyUniqueProcessNumberingIsGiven() {

        RetrieveProcessUseCase retrieveProcessUseCase = new RetrieveProcessUseCase();

        String uniqueProcessNumbering = "";

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }
}
