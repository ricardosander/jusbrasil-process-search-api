package io.github.ricardosander.jusbrasilprocessservice.web;

class ProcessNotFoundException extends RuntimeException {
    public ProcessNotFoundException(String processNumber) {
        super(String.format("%s was not found.", processNumber));
    }
}