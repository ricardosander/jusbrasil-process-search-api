package io.github.ricardosander.jusbrasilprocessservice.application;

public class RetrieveProcessUseCase {

    public void execute(String uniqueProcessNumbering) {
        throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
    }

    static class InvalidUniqueProcessNumberingException extends RuntimeException {

        public InvalidUniqueProcessNumberingException(String uniqueProcessNumbering) {
            super(String.format("%s is a invalid Unique Process Numbering.", uniqueProcessNumbering));
        }
    }
}
