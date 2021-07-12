package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class TRValidatorRule extends ValidatorBaseRule {

  private final Extractor extractor;

  public TRValidatorRule(Extractor extractor) {
    this.extractor = extractor;
  }

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    int tr = extractor.extractTR(uniqueProcessNumbering);
    if (!isTROnValidRange(tr)) {
      return false;
    }
    if (tr != 2 && tr != 12) {
      throw new NotSupportedUniqueProcessNumberingException(uniqueProcessNumbering);
    }
    return true;
  }

  private boolean isTROnValidRange(int tr) {
    return tr <= 27 || tr == 90;
  }
}
