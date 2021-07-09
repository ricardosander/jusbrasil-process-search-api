package io.github.ricardosander.jusbrasilprocessservice.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RetrieveProcessUseCaseTest {

    private RetrieveProcessUseCase retrieveProcessUseCase;

    @BeforeEach
    void setUp() {
        retrieveProcessUseCase = new RetrieveProcessUseCase();
    }

    @Test
    void shouldReturnProcess_whenTJALUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "07108025520188020001";

        Optional<RetrieveProcessUseCase.Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        assertThat(process).isPresent();
        assertThat(process.get().getId()).isEqualTo(uniqueProcessNumbering);
        assertThat(process.get().getClazz()).isEqualTo("Procedimento Comum Cível");
        assertThat(process.get().getArea()).isEqualTo("Cível");
        assertThat(process.get().getSubject()).isEqualTo("Dano Material");
        assertThat(process.get().getDistributionDate()).isEqualTo("02/05/2018 às 19:01 - Sorteio");
        assertThat(process.get().getJudge()).isEqualTo("José Cícero Alves da Silva");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 281.178,42");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 281.178,42");
        assertThat(process.get().getProcessParts()).isEqualTo("Autor: José Carlos Cerqueira Souza Filho\nAdvogado:  Vinicius Faria de Cerqueira\nRé: Cony Engenharia Ltda.\nAdvogado:  Carlos Henrique de Mendonça Brandão\nAdvogado:  Guilherme Freire Furtado\nAdvogada:  Maria Eugênia Barreiros de Mello\nAdvogado:  Vítor Reis de Araujo Carvalho");
        assertThat(process.get().getMovements()).isEqualTo("22/02/2021 - Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso");
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenNullUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = null;

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenEmptyUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "";

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }

    @Test
    void shouldThrowsInvalidUniqueProcessNumberingException_whenSpaceUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = " ";

        assertThatExceptionOfType(RetrieveProcessUseCase.InvalidUniqueProcessNumberingException.class)
                .isThrownBy(() -> retrieveProcessUseCase.execute(uniqueProcessNumbering));
    }
}
