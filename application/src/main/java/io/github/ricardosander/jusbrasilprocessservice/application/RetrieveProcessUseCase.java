package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.Optional;

class RetrieveProcessUseCase {

    private final RetrieveProcessGateway retrieveProcessGateway;

    public RetrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
        this.retrieveProcessGateway = retrieveProcessGateway;
    }

    public Optional<Process> execute(String uniqueProcessNumbering) {

        String sanitizedUniqueProcessNumbering = sanitizeUniqueProcessNumbering(uniqueProcessNumbering);

        if (isInvalid(sanitizedUniqueProcessNumbering))
            throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
        return retrieveProcessGateway.execute(sanitizedUniqueProcessNumbering);
    }

    private String sanitizeUniqueProcessNumbering(String originalUniqueProcessNumbering) {
        return Optional.ofNullable(originalUniqueProcessNumbering)
            .map(s -> s.replaceAll("\\D", ""))
            .orElse(null);
    }

    private boolean isInvalid(String uniqueProcessNumbering) {
        return uniqueProcessNumbering == null || uniqueProcessNumbering.trim().isEmpty();
    }
}
