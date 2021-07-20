package io.github.ricardosander.jusbrasilprocessservice.process;

import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumberingFactory;

import java.util.Optional;

public class RetrieveProcessUseCase {

  private final RetrieveProcessGateway retrieveProcessGateway;

  public RetrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
    this.retrieveProcessGateway = retrieveProcessGateway;
  }

  public Optional<Process> execute(String uniqueProcessNumbering) {

    UniqueProcessNumbering upn = UniqueProcessNumberingFactory.create(uniqueProcessNumbering);

    return retrieveProcessGateway.execute(upn);
  }

}
