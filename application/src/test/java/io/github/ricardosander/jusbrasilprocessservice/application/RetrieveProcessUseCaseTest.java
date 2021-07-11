package io.github.ricardosander.jusbrasilprocessservice.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenUniqueProcessNumberingGivenIsLessThanMinimumLength() {

        String uniqueProcessNumbering = "5120188120001";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenUniqueProcessNumberingGivenIsMoreThanMaximumLength() {

        String uniqueProcessNumbering = "108219015120188120001";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenGivenUniqueProcessNumberingHasInvalidTR() {

        String uniqueProcessNumbering = "08219015120188280001";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsNotSupportedUniqueProcessNumberingException_whenGivenUniqueProcessNumberingTRIsNotSupported() {

        String uniqueProcessNumbering = "08219015120188900001";

        assertThatExceptionOfType(NotSupportedUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenGivenUniqueProcessNumberingJIsInvalid() {

        String uniqueProcessNumbering = "08219015120180120001";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsNotSupportedUniqueProcessNumberingException_whenGivenUniqueProcessNumberingJIsNotSupported() {

        String uniqueProcessNumbering = "08219015120189120001";

        assertThatExceptionOfType(NotSupportedUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenGivenUniqueProcessNumberingJIHasFutureYear() {

        int futureYear = LocalDate.now().getYear() + 1;
        String uniqueProcessNumbering = String.format("082190151%s8120001", futureYear);

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenGivenUniqueProcessNumberingJIHasYearBeforeRepublic() {

        String uniqueProcessNumbering = "08219015118888120001";

        assertThatExceptionOfType(InvalidUniqueProcessNumberingException.class)
            .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }
}
