package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

class MinimumLengthValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.length() > 13;
  }
}
