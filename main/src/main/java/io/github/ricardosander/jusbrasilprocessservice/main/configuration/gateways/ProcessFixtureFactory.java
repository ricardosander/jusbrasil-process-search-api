package io.github.ricardosander.jusbrasilprocessservice.main.configuration.gateways;

import io.github.ricardosander.jusbrasilprocessservice.process.Process;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

class ProcessFixtureFactory {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");

    private ProcessFixtureFactory() {
    }

    static Map<String, Process> createProcesses() {
        return Map.of(
                "07108025520188020001", createsTJALProcess(),
                "08219015120188120001", createsTJMSProcess()
        );
    }

    static Process createsTJALProcess() {
        return Process.Builder.builder()
                .clazz("Procedimento Comum Cível")
                .area("Cível")
                .subject("Dano Material")
                .judge("José Cícero Alves da Silva")
                .distributionDate(
                        createsDateTime("02/05/2018 19:01"),
                        "Sorteio"
                )
                .shareValue("R$ 281.178,42")
                .processParts("Autor", "José Carlos Cerqueira Souza Filho")
                .processParts("Autor", "Advogado", "Vinicius Faria de Cerqueira")
                .processParts("Ré", "Cony Engenharia Ltda.")
                .processParts("Ré", "Advogado", "Carlos Henrique de Mendonça Brandão")
                .processParts("Ré", "Advogado", "Guilherme Freire Furtado")
                .processParts("Ré", "Advogada", "Maria Eugênia Barreiros de Mello")
                .processParts("Ré", "Advogado", "Vítor Reis de Araujo Carvalho")
                .movements(
                        createsDate("22/02/2021"),
                        "Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso"
                ).build();
    }

    static Process createsTJMSProcess() {
        return Process.Builder.builder()
                .clazz("Procedimento Comum Cível")
                .area("Cível")
                .subject("Enquadramento")
                .judge("Fernando Paes de Campos")
                .distributionDate(
                        createsDateTime("30/07/2018 12:39"),
                        "Automática"
                )
                .shareValue("R$ 10.000,00")
                .processParts("Autora", "Leidi Silva Ormond Galvão")
                .processParts("Autora", "Advogada", "Adriana Catelan Skowronski")
                .processParts("Autora", "Advogada", "Ana Silvia Pessoa Salgado de Moura")
                .processParts("Réu", "Estado de Mato Grosso do Sul")
                .processParts("Réu", "RepreLeg", "Procuradoria Geral do Estado de Mato Grosso do Sul")
                .movements(
                        createsDate("17/07/2020"),
                        "Guia de Recolhimento Judicial Emitida"
                ).build();
    }

    private static LocalDateTime createsDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    private static LocalDate createsDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}