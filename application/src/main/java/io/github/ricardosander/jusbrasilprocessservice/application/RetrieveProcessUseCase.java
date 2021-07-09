package io.github.ricardosander.jusbrasilprocessservice.application;

public class RetrieveProcessUseCase {

    public void execute(String uniqueProcessNumbering) {
        throw new InvalidUniqueProcessNumbering(uniqueProcessNumbering);
    }

    static class InvalidUniqueProcessNumbering extends RuntimeException {

        public InvalidUniqueProcessNumbering(String uniqueProcessNumbering) {
            super(String.format("%s is a invalid Unique Process Numbering.", uniqueProcessNumbering));
        }
    }
}
