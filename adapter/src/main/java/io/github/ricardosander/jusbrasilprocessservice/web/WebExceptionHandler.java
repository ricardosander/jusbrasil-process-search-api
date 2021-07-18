package io.github.ricardosander.jusbrasilprocessservice.web;

import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.InvalidUniqueProcessNumberingException;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.NotSupportedUniqueProcessNumberingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUniqueProcessNumberingException.class)
    public void handle(InvalidUniqueProcessNumberingException exception) {
        LOGGER.info(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotSupportedUniqueProcessNumberingException.class)
    public void handle(NotSupportedUniqueProcessNumberingException exception) {
        LOGGER.warn(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProcessNotFoundException.class)
    public void handle(ProcessNotFoundException exception) {
        LOGGER.warn(exception.getMessage());
    }
}
