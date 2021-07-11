package io.github.ricardosander.jusbrasilprocessservice.application;

class NotSupportedUniqueProcessNumberingException extends RuntimeException {

    public NotSupportedUniqueProcessNumberingException(String uniqueProcessNumbering) {
        super(String.format("%s is not supported.", uniqueProcessNumbering));
    }
}
