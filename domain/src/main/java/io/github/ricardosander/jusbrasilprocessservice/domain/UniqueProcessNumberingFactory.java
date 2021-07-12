package io.github.ricardosander.jusbrasilprocessservice.domain;

public class UniqueProcessNumberingFactory {

  public UniqueProcessNumbering create(String uniqueProcessNumbering) {
    return new UniqueProcessNumbering(uniqueProcessNumbering);
  }
}
