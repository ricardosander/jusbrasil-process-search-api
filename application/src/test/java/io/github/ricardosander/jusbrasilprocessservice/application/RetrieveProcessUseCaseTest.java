package io.github.ricardosander.jusbrasilprocessservice.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RetrieveProcessUseCaseTest {

    private RetrieveProcessUseCase retrieveProcessUseCase;

    @BeforeEach
    void setUp() {

        Map<String, Process> processes = createProcesses();

        RetrieveProcessGateway retrieveProcessGateway = uniqueProcessNumbering ->
                Optional.ofNullable(processes.get(uniqueProcessNumbering));

        retrieveProcessUseCase = new RetrieveProcessUseCase(retrieveProcessGateway);
    }

    @Test
    void shouldReturnProcess_whenTJALUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "07108025520188020001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        assertThat(process).isPresent();
        assertThat(process.get().getId()).isEqualTo(uniqueProcessNumbering);
        assertThat(process.get().getClazz()).isEqualTo("Procedimento Comum Cível");
        assertThat(process.get().getArea()).isEqualTo("Cível");
        assertThat(process.get().getSubject()).isEqualTo("Dano Material");
        assertThat(process.get().getDistributionDate()).isEqualTo("02/05/2018 às 19:01 - Sorteio");
        assertThat(process.get().getJudge()).isEqualTo("José Cícero Alves da Silva");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 281.178,42");
        assertThat(process.get().getProcessParts()).isEqualTo("Autor \tJosé Carlos Cerqueira Souza Filho\nAdvogado:  Vinicius Faria de Cerqueira\nRé \tCony Engenharia Ltda.\nAdvogado:  Carlos Henrique de Mendonça Brandão \nAdvogado:  Guilherme Freire Furtado \nAdvogada:  Maria Eugênia Barreiros de Mello \nAdvogado:  Vítor Reis de Araujo Carvalho");
        assertThat(process.get().getMovements()).isEqualTo("22/02/2021\t\tRemetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso");
    }

    @Test
    void shouldReturnProcess_whenTJMSUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "08219015120188120001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        assertThat(process).isPresent();
        assertThat(process.get().getId()).isEqualTo(uniqueProcessNumbering);
        assertThat(process.get().getClazz()).isEqualTo("Procedimento Comum Cível");
        assertThat(process.get().getArea()).isEqualTo("Cível");
        assertThat(process.get().getSubject()).isEqualTo("Enquadramento");
        assertThat(process.get().getDistributionDate()).isEqualTo("30/07/2018 às 12:39 - Automática");
        assertThat(process.get().getJudge()).isEqualTo("Fernando Paes de Campos");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 10.000,00");
        assertThat(process.get().getProcessParts()).isEqualTo("Autora \tLeidi Silva Ormond Galvão\nAdvogada:  Adriana Catelan Skowronski  \nAdvogada:  Ana Silvia Pessoa Salgado de Moura\nRéu \tEstado de Mato Grosso do Sul\nRepreLeg:  Procuradoria Geral do Estado de Mato Grosso do Sul");
        assertThat(process.get().getMovements()).isEqualTo("17/07/2020\t\tGuia de Recolhimento Judicial Emitida");
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

    private Map<String, Process> createProcesses() {
        return Map.of(
                "07108025520188020001", createProcess(
                        "07108025520188020001",
                        "Dano Material",
                        "02/05/2018 às 19:01 - Sorteio",
                        "José Cícero Alves da Silva",
                        "R$ 281.178,42",
                        "Autor \tJosé Carlos Cerqueira Souza Filho\n" +
                                "Advogado:  Vinicius Faria de Cerqueira\n" +
                                "Ré \tCony Engenharia Ltda.\n" +
                                "Advogado:  Carlos Henrique de Mendonça Brandão \n" +
                                "Advogado:  Guilherme Freire Furtado \n" +
                                "Advogada:  Maria Eugênia Barreiros de Mello \n" +
                                "Advogado:  Vítor Reis de Araujo Carvalho",
                        "22/02/2021\t\tRemetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso"
                ),
                "08219015120188120001", createProcess(
                        "08219015120188120001",
                        "Enquadramento",
                        "30/07/2018 às 12:39 - Automática",
                        "Fernando Paes de Campos",
                        "R$ 10.000,00",
                        "Autora \tLeidi Silva Ormond Galvão\n" +
                                "Advogada:  Adriana Catelan Skowronski  \n" +
                                "Advogada:  Ana Silvia Pessoa Salgado de Moura\n" +
                                "Réu \tEstado de Mato Grosso do Sul\n" +
                                "RepreLeg:  Procuradoria Geral do Estado de Mato Grosso do Sul",
                        "17/07/2020\t\tGuia de Recolhimento Judicial Emitida"
                )
        );
    }

    private Process createProcess(
            String id,
            String subject,
            String distributionDate,
            String judge,
            String shareValue,
            String processParts,
            String movements
    ) {
        return new Process(
                id,
                "Procedimento Comum Cível",
                "Cível",
                subject,
                distributionDate,
                judge,
                shareValue,
                processParts,
                movements
        );
    }
}
