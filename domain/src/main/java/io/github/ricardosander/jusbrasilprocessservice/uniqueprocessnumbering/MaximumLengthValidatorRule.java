package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class MaximumLengthValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return uniqueProcessNumbering.length() <= 20;
  }
}
