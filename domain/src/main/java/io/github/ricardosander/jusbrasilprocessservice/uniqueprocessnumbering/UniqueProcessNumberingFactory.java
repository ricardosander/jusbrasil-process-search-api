package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

public class UniqueProcessNumberingFactory {

  private static final ValidatorRule HANDLER = ValidatorRuleFactory.getInstance().getRule();
  private static final Sanitizer SANITIZER = new Sanitizer();

  public static UniqueProcessNumbering create(String uniqueProcessNumbering) {

    String sanitizedUPN = SANITIZER.sanitize(uniqueProcessNumbering);

    if (HANDLER.handle(sanitizedUPN))
      return new UniqueProcessNumbering(sanitizedUPN);
    throw new InvalidUniqueProcessNumberingException(uniqueProcessNumbering);
  }

}
