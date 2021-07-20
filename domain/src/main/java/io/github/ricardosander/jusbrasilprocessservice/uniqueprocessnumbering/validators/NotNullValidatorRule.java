package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators;

class NotNullValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return uniqueProcessNumbering != null;
  }

}
