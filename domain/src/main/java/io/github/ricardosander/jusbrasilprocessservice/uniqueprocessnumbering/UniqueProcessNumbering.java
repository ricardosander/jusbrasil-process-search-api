package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

public class UniqueProcessNumbering {

  private final String value;

  UniqueProcessNumbering(String uniqueProcessNumbering) {
    value = uniqueProcessNumbering;
  }

  public String getValue() {
    return value;
  }
}
