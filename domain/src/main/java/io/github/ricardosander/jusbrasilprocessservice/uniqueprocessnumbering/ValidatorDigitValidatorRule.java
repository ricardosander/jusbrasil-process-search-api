package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class ValidatorDigitValidatorRule extends ValidatorBaseRule {

  private final Extractor extractor;

  public ValidatorDigitValidatorRule(Extractor extractor) {
    this.extractor = extractor;
  }

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    long validatorDigit = extractor.extractValidatorDigit(uniqueProcessNumbering);
    long toValidate = extractor.extractNumberToValidate(uniqueProcessNumbering);
    long computedNumberToValidate = computesNumberToValidate(toValidate);
    return computedNumberToValidate == validatorDigit;
  }

  private long computesNumberToValidate(long toValidate) {
    return 98 - ((toValidate * 100) % 97);
  }
}
