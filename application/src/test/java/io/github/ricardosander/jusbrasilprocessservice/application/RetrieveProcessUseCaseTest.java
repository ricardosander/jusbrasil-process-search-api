package io.github.ricardosander.jusbrasilprocessservice.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class RetrieveProcessUseCaseTest {

    private RetrieveProcessUseCase retrieveProcessUseCase;

    @BeforeEach
    void setUp() {
        InMemoryRetrieveProcessGateway retrieveProcessGateway = new InMemoryRetrieveProcessGateway();
        retrieveProcessUseCase = new RetrieveProcessUseCase(retrieveProcessGateway);
    }

    @Test
    void shouldReturnProcess_whenTJALUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "07108025520188020001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        Process expected = ProcessFixtureFactory.createsTJALProcess();

        assertThat(process)
                .isPresent()
                .contains(expected);
        assertThat(process.get()).isNotSameAs(expected);
    }

    @Test
    void shouldReturnProcess_whenTJMSUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "08219015120188120001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        Process expected = ProcessFixtureFactory.createsTJMSProcess();

        assertThat(process)
                .isPresent()
                .contains(expected);
        assertThat(process.get()).isNotSameAs(expected);
    }

    @Test
    void shouldReturnProcess_whenFormattedTJALUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "0710802-55.2018.8.02.0001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        Process expected = ProcessFixtureFactory.createsTJALProcess();

        assertThat(process)
            .isPresent()
            .contains(expected);
        assertThat(process.get()).isNotSameAs(expected);
    }

    @Test
    void shouldReturnProcess_whenFormattedTJMSUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "0821901-51.2018.8.12.0001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        Process expected = ProcessFixtureFactory.createsTJMSProcess();

        assertThat(process)
            .isPresent()
            .contains(expected);
        assertThat(process.get()).isNotSameAs(expected);
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenNullUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = null;

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenEmptyUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenSpaceUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = " ";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }
}
