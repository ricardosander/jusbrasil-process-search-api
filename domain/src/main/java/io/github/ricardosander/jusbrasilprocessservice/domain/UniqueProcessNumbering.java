package io.github.ricardosander.jusbrasilprocessservice.domain;

public class UniqueProcessNumbering {

  private final String value;

  public UniqueProcessNumbering(String uniqueProcessNumbering) {
    value = uniqueProcessNumbering;
  }

  public String getValue() {
    return value;
  }
}
