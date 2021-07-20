package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

class MaximumLengthValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.length() <= 20;
  }
}
