package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

import java.util.Optional;

class Sanitizer {

  public String sanitize(String originalUniqueProcessNumbering) {
    return Optional.ofNullable(originalUniqueProcessNumbering)
        .map(this::removesNotNumericCharacters)
        .orElse(null);
  }

  private String removesNotNumericCharacters(String s) {
    return s.replaceAll("\\D", "");
  }
}
