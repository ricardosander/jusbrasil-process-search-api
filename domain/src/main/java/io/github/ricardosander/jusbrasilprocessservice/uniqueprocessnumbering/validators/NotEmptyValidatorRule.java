package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

class NotEmptyValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return !uniqueProcessNumbering.trim().isEmpty();
  }
}
