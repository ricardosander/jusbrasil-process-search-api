package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

public class NotSupportedUniqueProcessNumberingException extends RuntimeException {

    public NotSupportedUniqueProcessNumberingException(String uniqueProcessNumbering) {
        super(String.format("%s is not supported.", uniqueProcessNumbering));
    }
}
