package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.Optional;

public class RetrieveProcessUseCase {

    private final RetrieveProcessGateway retrieveProcessGateway;

    public RetrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
        this.retrieveProcessGateway = retrieveProcessGateway;
    }

    public Optional<Process> execute(String uniqueProcessNumbering) {
        if (uniqueProcessNumbering == null || uniqueProcessNumbering.trim().isEmpty()) {
            throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
        }

        return retrieveProcessGateway.execute(uniqueProcessNumbering);
    }
}
