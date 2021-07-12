package io.github.ricardosander.jusbrasilprocessservice.domain;

public class InvalidUniqueProcessNumberingException extends RuntimeException {

    public InvalidUniqueProcessNumberingException(String uniqueProcessNumbering) {
        super(String.format("%s is an invalid Unique Process Numbering.", uniqueProcessNumbering));
    }
}
