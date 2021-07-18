package io.github.ricardosander.jusbrasilprocessservice.web.configuration;

import io.github.ricardosander.jusbrasilprocessservice.application.Process;

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
    return Map.of("07108025520188020001", createsProcess());
  }

  static Process createsProcess() {
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

  private static LocalDateTime createsDateTime(String date) {
    return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
  }

  private static LocalDate createsDate(String date) {
    return LocalDate.parse(date, DATE_FORMATTER);
  }
}
