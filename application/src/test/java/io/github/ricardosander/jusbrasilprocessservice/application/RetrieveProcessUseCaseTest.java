package io.github.ricardosander.jusbrasilprocessservice.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        assertThat(process.get().getClazz()).isEqualTo("Procedimento Comum Cível");
        assertThat(process.get().getArea()).isEqualTo("Cível");
        assertThat(process.get().getSubject()).isEqualTo("Dano Material");
        assertThat(process.get().getDistributionDate().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm"))).isEqualTo("02/05/2018 19:01");
        assertThat(process.get().getDistributionDate().getType()).isEqualTo("Sorteio");
        assertThat(process.get().getJudge()).isEqualTo("José Cícero Alves da Silva");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 281.178,42");
        assertThat(process.get().getProcessParts()).hasSize(7);
        assertThat(process.get().getProcessParts()).contains(
                new ProcessPart("Autor", "José Carlos Cerqueira Souza Filho"),
                new ProcessPart("Autor", "Advogado", "Vinicius Faria de Cerqueira"),
                new ProcessPart("Ré", "Cony Engenharia Ltda."),
                new ProcessPart("Ré", "Advogado", "Carlos Henrique de Mendonça Brandão"),
                new ProcessPart("Ré", "Advogado", "Guilherme Freire Furtado"),
                new ProcessPart("Ré", "Advogada", "Maria Eugênia Barreiros de Mello"),
                new ProcessPart("Ré", "Advogado", "Vítor Reis de Araujo Carvalho")
        );
        assertThat(process.get().getMovements()).contains(
                new Movement(
                        LocalDate.parse("22/02/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        "Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso"
                )
        );
    }

    @Test
    void shouldReturnProcess_whenTJMSUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "08219015120188120001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        assertThat(process).isPresent();
        assertThat(process.get().getClazz()).isEqualTo("Procedimento Comum Cível");
        assertThat(process.get().getArea()).isEqualTo("Cível");
        assertThat(process.get().getSubject()).isEqualTo("Enquadramento");
        assertThat(process.get().getDistributionDate().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).isEqualTo("30/07/2018 12:39");
        assertThat(process.get().getDistributionDate().getType()).isEqualTo("Automática");
        assertThat(process.get().getJudge()).isEqualTo("Fernando Paes de Campos");
        assertThat(process.get().getShareValue()).isEqualTo("R$ 10.000,00");
        assertThat(process.get().getProcessParts()).hasSize(5);
        assertThat(process.get().getProcessParts()).contains(
                new ProcessPart("Autora", "Leidi Silva Ormond Galvão"),
                new ProcessPart("Autora", "Advogada", "Adriana Catelan Skowronski"),
                new ProcessPart("Autora", "Advogada", "Ana Silvia Pessoa Salgado de Moura"),
                new ProcessPart("Réu", "Estado de Mato Grosso do Sul"),
                new ProcessPart("Réu", "RepreLeg", "Procuradoria Geral do Estado de Mato Grosso do Sul")
        );
        assertThat(process.get().getMovements()).contains(
                new Movement(
                        LocalDate.parse("17/07/2020", DateTimeFormatter.ofPattern("dd/MM/yyy")),
                        "Guia de Recolhimento Judicial Emitida"
                )
        );
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
                        "Dano Material",
                        LocalDateTime.parse("02/05/2018 19:01", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        "Sorteio",
                        "José Cícero Alves da Silva",
                        "R$ 281.178,42",
                        List.of(
                                new ProcessPart("Autor", "José Carlos Cerqueira Souza Filho"),
                                new ProcessPart("Autor", "Advogado", "Vinicius Faria de Cerqueira"),
                                new ProcessPart("Ré", "Cony Engenharia Ltda."),
                                new ProcessPart("Ré", "Advogado", "Carlos Henrique de Mendonça Brandão"),
                                new ProcessPart("Ré", "Advogado", "Guilherme Freire Furtado"),
                                new ProcessPart("Ré", "Advogada", "Maria Eugênia Barreiros de Mello"),
                                new ProcessPart("Ré", "Advogado", "Vítor Reis de Araujo Carvalho")
                        ),
                        List.of(
                                new Movement(
                                        LocalDate.parse("22/02/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                        "Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso"
                                )
                        )
                ),
                "08219015120188120001", createProcess(
                        "Enquadramento",
                        LocalDateTime.parse("30/07/2018 12:39", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        "Automática",
                        "Fernando Paes de Campos",
                        "R$ 10.000,00",
                        List.of(
                                new ProcessPart("Autora", "Leidi Silva Ormond Galvão"),
                                new ProcessPart("Autora", "Advogada", "Adriana Catelan Skowronski"),
                                new ProcessPart("Autora", "Advogada", "Ana Silvia Pessoa Salgado de Moura"),
                                new ProcessPart("Réu", "Estado de Mato Grosso do Sul"),
                                new ProcessPart("Réu", "RepreLeg", "Procuradoria Geral do Estado de Mato Grosso do Sul")
                        ),
                        List.of(
                                new Movement(
                                        LocalDate.parse("17/07/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                        "Guia de Recolhimento Judicial Emitida"
                                )
                        )
                )
        );
    }

    private Process createProcess(
            String subject,
            LocalDateTime distributionDate,
            String distributionType,
            String judge,
            String shareValue,
            List<ProcessPart> processParts,
            List<Movement> movements
    ) {
        return new Process(
                "Procedimento Comum Cível",
                "Cível",
                subject,
                new DistributionDate(distributionDate, distributionType),
                judge,
                shareValue,
                processParts,
                movements
        );
    }
}
