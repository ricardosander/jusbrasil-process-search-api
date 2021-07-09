package io.github.ricardosander.jusbrasilprocessservice.application;

class InvalidUniqueProcessNumberingException extends RuntimeException {

    public InvalidUniqueProcessNumberingException(String uniqueProcessNumbering) {
        super(String.format("%s is a invalid Unique Process Numbering.", uniqueProcessNumbering));
    }
}
