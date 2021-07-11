package io.github.ricardosander.jusbrasilprocessservice.application;

import java.time.LocalDate;
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

        if (isNotSupported(sanitizedUniqueProcessNumbering)) {
            throw new NotSupportedUniqueProcessNumberingException(uniqueProcessNumbering);
        }
        return retrieveProcessGateway.execute(sanitizedUniqueProcessNumbering);
    }

    private String sanitizeUniqueProcessNumbering(String originalUniqueProcessNumbering) {
        return Optional.ofNullable(originalUniqueProcessNumbering)
            .map(s -> s.replaceAll("\\D", ""))
            .orElse(null);
    }

    private boolean isInvalid(String uniqueProcessNumbering) {
        return uniqueProcessNumbering == null
            || uniqueProcessNumbering.trim().isEmpty()
            || uniqueProcessNumbering.length() < 14
            || uniqueProcessNumbering.length() > 20
            || isInvalidTR(uniqueProcessNumbering)
            || isInvalidJ(uniqueProcessNumbering)
            || isInvalidYear(uniqueProcessNumbering)
            || isInvalidNumber(uniqueProcessNumbering)
            || isInvalidValidator(uniqueProcessNumbering);
    }

    private boolean isNotSupported(String uniqueProcessNumbering) {
        return isTRNotSupported(uniqueProcessNumbering)
            || isJNotSupported(uniqueProcessNumbering);
    }

    private boolean isInvalidTR(String uniqueProcessNumbering) {
        int tr = extractTR(uniqueProcessNumbering);
        return tr > 27 && tr != 90;
    }

    private boolean isInvalidJ(String uniqueProcessNumbering) {
        return 0 == extractJ(uniqueProcessNumbering);
    }

    private boolean isInvalidYear(String uniqueProcessNumbering) {
        int year = Integer.parseInt(uniqueProcessNumbering.substring(
            uniqueProcessNumbering.length() - 11,
            uniqueProcessNumbering.length() - 7
        ));
        return year < 1889 || year > LocalDate.now().getYear();
    }

    private boolean isInvalidNumber(String uniqueProcessNumbering) {
        return 0 == Integer.parseInt(uniqueProcessNumbering.substring(
            0,
            uniqueProcessNumbering.length() - 13
        ));
    }

    private boolean isInvalidValidator(String uniqueProcessNumbering) {
        long validatorDigit = Integer.parseInt(uniqueProcessNumbering.substring(
            uniqueProcessNumbering.length() - 13,
            uniqueProcessNumbering.length() - 11
        ));
        long toValidate = Long.parseLong(
            uniqueProcessNumbering.substring(0, uniqueProcessNumbering.length() - 13) +
                uniqueProcessNumbering.substring(uniqueProcessNumbering.length() - 11)
        );
        return (98 - ((toValidate * 100) % 97)) != validatorDigit;
    }

    private boolean isTRNotSupported(String uniqueProcessNumbering) {
        int tr = extractTR(uniqueProcessNumbering);
        return tr != 2 && tr != 12;
    }

    private boolean isJNotSupported(String uniqueProcessNumbering) {
        return 8 != extractJ(uniqueProcessNumbering);
    }

    private int extractTR(String uniqueProcessNumbering) {
        return Integer.parseInt(uniqueProcessNumbering.substring(
            uniqueProcessNumbering.length() - 6,
            uniqueProcessNumbering.length() - 4
        ));
    }

    private int extractJ(String uniqueProcessNumbering) {
        return Integer.parseInt(uniqueProcessNumbering.substring(
            uniqueProcessNumbering.length() - 7,
            uniqueProcessNumbering.length() - 6
        ));
    }
}
