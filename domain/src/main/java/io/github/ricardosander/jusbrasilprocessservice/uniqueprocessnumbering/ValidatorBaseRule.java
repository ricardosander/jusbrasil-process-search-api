package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

abstract class ValidatorBaseRule implements ValidatorRule {

  private ValidatorRule next;

  public boolean handle(String uniqueProcessNumbering) {
    if (isValid(uniqueProcessNumbering)) {
      return handleNext(uniqueProcessNumbering);
    }
    return false;
  }

  private boolean handleNext(String uniqueProcessNumbering) {
    if (this.next == null) {
      return true;
    }
    return next.handle(uniqueProcessNumbering);
  }

  abstract boolean isValid(String uniqueProcessNumbering);

  public void setNext(ValidatorRule next) {
    this.next = next;
  }
}
