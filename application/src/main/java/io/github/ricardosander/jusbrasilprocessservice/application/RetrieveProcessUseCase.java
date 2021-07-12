package io.github.ricardosander.jusbrasilprocessservice.application;

import io.github.ricardosander.jusbrasilprocessservice.domain.UniqueProcessNumbering;
import io.github.ricardosander.jusbrasilprocessservice.domain.UniqueProcessNumberingFactory;

import java.util.Optional;

class RetrieveProcessUseCase {

  private final RetrieveProcessGateway retrieveProcessGateway;

  public RetrieveProcessUseCase(RetrieveProcessGateway retrieveProcessGateway) {
    this.retrieveProcessGateway = retrieveProcessGateway;
  }

  public Optional<Process> execute(String uniqueProcessNumbering) {

    UniqueProcessNumbering upn =
        new UniqueProcessNumberingFactory().create(uniqueProcessNumbering);

    return retrieveProcessGateway.execute(upn);
  }

}
