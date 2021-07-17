package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class NotNullValidatorRule extends ValidatorBaseRule {

  @Override
  boolean isValid(String uniqueProcessNumbering) {
    return uniqueProcessNumbering != null;
  }

}
