package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

import java.time.LocalDate;

class YearValidatorRule extends ValidatorBaseRule {

  private final Extractor extractor;

  public YearValidatorRule(Extractor extractor) {
    this.extractor = extractor;
  }

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    int year = extractor.extractYear(uniqueProcessNumbering);
    return isYearOnValidRange(year);
  }

  private boolean isYearOnValidRange(int year) {
    return year >= 1889 && year <= LocalDate.now().getYear();
  }
}
