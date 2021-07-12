package io.github.ricardosander.jusbrasilprocessservice.domain;

import java.time.LocalDate;
import java.util.Optional;

public class UniqueProcessNumberingFactory {

  public UniqueProcessNumbering create(String uniqueProcessNumbering) {

    String sanitizedUniqueProcessNumbering = sanitizeUniqueProcessNumbering(uniqueProcessNumbering);

    if (isInvalid(sanitizedUniqueProcessNumbering)) {
      throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
    }

    if (isNotSupported(sanitizedUniqueProcessNumbering)) {
      throw new NotSupportedUniqueProcessNumberingException(uniqueProcessNumbering);
    }

    return new UniqueProcessNumbering(sanitizedUniqueProcessNumbering);
  }

  private String sanitizeUniqueProcessNumbering(String originalUniqueProcessNumbering) {
    return Optional.ofNullable(originalUniqueProcessNumbering)
        .map(this::removesNotNumericCharacters)
        .orElse(null);
  }

  private boolean isInvalid(String uniqueProcessNumbering) {
    return isNull(uniqueProcessNumbering)
        || isEmpty(uniqueProcessNumbering)
        || isLessThanMinimumLength(uniqueProcessNumbering)
        || isMoreThanMaximumLength(uniqueProcessNumbering)
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

  private String removesNotNumericCharacters(String s) {
    return s.replaceAll("\\D", "");
  }

  private boolean isNull(String uniqueProcessNumbering) {
    return uniqueProcessNumbering == null;
  }

  private boolean isEmpty(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.trim().isEmpty();
  }

  private boolean isLessThanMinimumLength(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.length() < 14;
  }

  private boolean isMoreThanMaximumLength(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.length() > 20;
  }

  private boolean isInvalidTR(String uniqueProcessNumbering) {
    int tr = extractTR(uniqueProcessNumbering);
    return isTROnValidRange(tr);
  }

  private boolean isInvalidJ(String uniqueProcessNumbering) {
    return 0 == extractJ(uniqueProcessNumbering);
  }

  private boolean isInvalidYear(String uniqueProcessNumbering) {
    int year = extractYear(uniqueProcessNumbering);
    return isYearOnValidRange(year);
  }

  private boolean isInvalidNumber(String uniqueProcessNumbering) {
    return 0 == extractNumber(uniqueProcessNumbering);
  }

  private boolean isInvalidValidator(String uniqueProcessNumbering) {
    long validatorDigit = extractValidatorDigit(uniqueProcessNumbering);
    long toValidate = extractNumberToValidate(uniqueProcessNumbering);
    long computedNumberToValidate = computesNumberToValidate(toValidate);
    return computedNumberToValidate != validatorDigit;
  }

  private boolean isTRNotSupported(String uniqueProcessNumbering) {
    int tr = extractTR(uniqueProcessNumbering);
    return tr != 2 && tr != 12;
  }

  private boolean isJNotSupported(String uniqueProcessNumbering) {
    return 8 != extractJ(uniqueProcessNumbering);
  }

  private int extractTR(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 6, 4);
  }

  private boolean isTROnValidRange(int tr) {
    return tr > 27 && tr != 90;
  }

  private int extractJ(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 7, 6);
  }

  private int extractYear(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 11, 7);
  }

  private boolean isYearOnValidRange(int year) {
    return year < 1889 || year > LocalDate.now().getYear();
  }

  private int extractNumber(String uniqueProcessNumbering) {
    return Integer.parseInt(uniqueProcessNumbering.substring(
        0,
        uniqueProcessNumbering.length() - 13
    ));
  }

  private int extractValidatorDigit(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 13, 11);
  }

  private long extractNumberToValidate(String uniqueProcessNumbering) {
    return Long.parseLong(
        uniqueProcessNumbering.substring(0, uniqueProcessNumbering.length() - 13) +
            uniqueProcessNumbering.substring(uniqueProcessNumbering.length() - 11)
    );
  }

  private long computesNumberToValidate(long toValidate) {
    return 98 - ((toValidate * 100) % 97);
  }

  private int extractsIntByPosition(
      String uniqueProcessNumbering,
      int relativeStartPosition,
      int relativeEndPosition
  ) {
    return Integer.parseInt(
        extractsByRelativePosition(
            uniqueProcessNumbering,
            relativeStartPosition,
            relativeEndPosition
        )
    );
  }

  private String extractsByRelativePosition(
      String uniqueProcessNumbering,
      int relativeStartPosition,
      int relativeEndPosition
  ) {
    return uniqueProcessNumbering.substring(
        uniqueProcessNumbering.length() - relativeStartPosition,
        uniqueProcessNumbering.length() - relativeEndPosition
    );
  }
}
