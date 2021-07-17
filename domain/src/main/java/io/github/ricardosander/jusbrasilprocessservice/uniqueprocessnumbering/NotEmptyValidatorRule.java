package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class NotEmptyValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return !uniqueProcessNumbering.trim().isEmpty();
  }
}
