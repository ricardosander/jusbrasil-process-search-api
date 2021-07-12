package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class ValidatorRuleFactory {

  private static ValidatorRuleFactory instance;

  private ValidatorRule rule;

  private ValidatorRuleFactory() {

    Extractor extractor = new Extractor();

    NotNullValidatorRule notNull =
        new NotNullValidatorRule();
    NotEmptyValidatorRule notEmpty =
        new NotEmptyValidatorRule();
    MinimumLengthValidatorRule minimumLength =
        new MinimumLengthValidatorRule();
    MaximumLengthValidatorRule maximumLength =
        new MaximumLengthValidatorRule();
    TRValidatorRule tr =
        new TRValidatorRule(extractor);
    JValidatorRule j =
        new JValidatorRule(extractor);
    YearValidatorRule year =
        new YearValidatorRule(extractor);
    NumberValidatorRule number =
        new NumberValidatorRule(extractor);
    ValidatorDigitValidatorRule validatorDigit =
        new ValidatorDigitValidatorRule(extractor);

    notNull.setNext(notEmpty);
    notEmpty.setNext(minimumLength);
    minimumLength.setNext(maximumLength);
    maximumLength.setNext(tr);
    tr.setNext(j);
    j.setNext(year);
    year.setNext(number);
    number.setNext(validatorDigit);

    rule = notNull;
  }

  public static ValidatorRuleFactory getInstance() {
    if (instance == null) { instance = new ValidatorRuleFactory(); }
    return instance;
  }

  public ValidatorRule getRule() {
    return rule;
  }
}
