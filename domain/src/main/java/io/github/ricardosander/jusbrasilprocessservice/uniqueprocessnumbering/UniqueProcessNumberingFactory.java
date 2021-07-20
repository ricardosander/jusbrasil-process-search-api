package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators.ValidatorRule;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.validators.ValidatorRuleFactory;

public class UniqueProcessNumberingFactory {

  private static final ValidatorRule HANDLER = ValidatorRuleFactory.getInstance().getRule();
  private static final Sanitizer SANITIZER = new Sanitizer();

  private UniqueProcessNumberingFactory() {
  }

  public static UniqueProcessNumbering create(String uniqueProcessNumbering) {

    String sanitizedUPN = SANITIZER.sanitize(uniqueProcessNumbering);

    if (HANDLER.handle(sanitizedUPN))
      return new UniqueProcessNumbering(sanitizedUPN);
    throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
  }

}
