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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

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

        Process expected = createsTJALProcess();

        assertThat(process)
                .isPresent()
                .contains(expected);
        assertThat(process.get()).isNotSameAs(expected);
    }

    @Test
    void shouldReturnProcess_whenTJMSUniqueProcessNumberingIsGiven() {

        String uniqueProcessNumbering = "08219015120188120001";

        Optional<Process> process = retrieveProcessUseCase.execute(uniqueProcessNumbering);

        Process expected = createsTJMSProcess();

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

    private Map<String, Process> createProcesses() {
        return Map.of(
                "07108025520188020001", createsTJALProcess(),
                "08219015120188120001", createsTJMSProcess()
        );
    }

    private Process createsTJALProcess() {
        return createProcess(
                "Dano Material",
                createsDateTime("02/05/2018 19:01"),
                "Sorteio",
                "José Cícero Alves da Silva",
                "R$ 281.178,42",
                List.of(
                        createsProcessPart("Autor", "José Carlos Cerqueira Souza Filho"),
                        createsProcessPart("Autor", "Advogado", "Vinicius Faria de Cerqueira"),
                        createsProcessPart("Ré", "Cony Engenharia Ltda."),
                        createsProcessPart("Ré", "Advogado", "Carlos Henrique de Mendonça Brandão"),
                        createsProcessPart("Ré", "Advogado", "Guilherme Freire Furtado"),
                        createsProcessPart("Ré", "Advogada", "Maria Eugênia Barreiros de Mello"),
                        createsProcessPart("Ré", "Advogado", "Vítor Reis de Araujo Carvalho")
                ),
                List.of(createsProcessMovement(
                        "22/02/2021",
                        "Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso"
                ))
        );
    }

    private Process createsTJMSProcess() {
        return createProcess(
                "Enquadramento",
                createsDateTime("30/07/2018 12:39"),
                "Automática",
                "Fernando Paes de Campos",
                "R$ 10.000,00",
                List.of(
                        createsProcessPart("Autora", "Leidi Silva Ormond Galvão"),
                        createsProcessPart("Autora", "Advogada", "Adriana Catelan Skowronski"),
                        createsProcessPart("Autora", "Advogada", "Ana Silvia Pessoa Salgado de Moura"),
                        createsProcessPart("Réu", "Estado de Mato Grosso do Sul"),
                        createsProcessPart("Réu", "RepreLeg", "Procuradoria Geral do Estado de Mato Grosso do Sul")
                ),
                List.of(
                        createsProcessMovement("17/07/2020", "Guia de Recolhimento Judicial Emitida")
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
                createsDistributionDate(distributionDate, distributionType),
                judge,
                shareValue,
                processParts,
                movements
        );
    }

    private LocalDateTime createsDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    private ProcessPart createsProcessPart(String type, String part) {
        return new ProcessPart(type, part);
    }

    private ProcessPart createsProcessPart(String type, String subType, String part) {
        return new ProcessPart(type, subType, part);
    }

    private Movement createsProcessMovement(String movementDate, String movementDescription) {
        return new Movement(createsDate(movementDate), movementDescription);
    }

    private DistributionDate createsDistributionDate(LocalDateTime distributionDate, String distributionType) {
        return new DistributionDate(distributionDate, distributionType);
    }

    private LocalDate createsDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
