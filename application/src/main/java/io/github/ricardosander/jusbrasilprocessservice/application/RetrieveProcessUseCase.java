package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.Optional;

public class RetrieveProcessUseCase {

    public Optional<Process> execute(String uniqueProcessNumbering) {
        if (uniqueProcessNumbering == null || uniqueProcessNumbering.trim().isEmpty()) {
            throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
        }

        return Optional.of(new Process(uniqueProcessNumbering));
    }

    static class Process {

        private final String id;

        Process(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getClazz() {
            return "Procedimento Comum Cível";
        }

        public String getArea() {
            return "Cível";
        }

        public String getSubject() {
            return "Dano Material";
        }

        public String getDistributionDate() {
            return "02/05/2018 às 19:01 - Sorteio";
        }

        public String getJudge() {
            return "José Cícero Alves da Silva";
        }

        public String getShareValue() {
            return "R$ 281.178,42";
        }

        public String getProcessParts() {
            return "Autor: José Carlos Cerqueira Souza Filho\nAdvogado:  Vinicius Faria de Cerqueira\nRé: Cony Engenharia Ltda.\nAdvogado:  Carlos Henrique de Mendonça Brandão\nAdvogado:  Guilherme Freire Furtado\nAdvogada:  Maria Eugênia Barreiros de Mello\nAdvogado:  Vítor Reis de Araujo Carvalho";
        }

        public String getMovements() {
            return "22/02/2021 - Remetido recurso eletrônico ao Tribunal de Justiça/Turma de recurso";
        }
    }

    static class InvalidUniqueProcessNumberingException extends RuntimeException {

        public InvalidUniqueProcessNumberingException(String uniqueProcessNumbering) {
            super(String.format("%s is a invalid Unique Process Numbering.", uniqueProcessNumbering));
        }
    }
}
