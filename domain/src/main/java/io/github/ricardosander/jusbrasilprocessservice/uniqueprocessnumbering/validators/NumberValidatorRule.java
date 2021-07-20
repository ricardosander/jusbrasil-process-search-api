package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

class NumberValidatorRule extends ValidatorBaseRule {

  private final Extractor extractor;

  public NumberValidatorRule(Extractor extractor) {
    this.extractor = extractor;
  }

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return 0 != extractor.extractNumber(uniqueProcessNumbering);
  }

}
