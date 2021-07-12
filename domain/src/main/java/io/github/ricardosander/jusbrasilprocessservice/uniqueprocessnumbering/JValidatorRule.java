package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class JValidatorRule extends ValidatorBaseRule {

  private final Extractor extractor;

  public JValidatorRule(Extractor extractor) {
    this.extractor = extractor;
  }

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    int j = extractor.extractJ(uniqueProcessNumbering);
    if (0 == j) {
      return false;
    }
    if (8 != j) {
      throw new NotSupportedUniqueProcessNumberingException(uniqueProcessNumbering);
    }
    return true;
  }
}
