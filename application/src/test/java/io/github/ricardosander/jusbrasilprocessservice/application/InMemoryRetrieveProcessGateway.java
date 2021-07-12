package io.github.ricardosander.jusbrasilprocessservice.application;

import io.github.ricardosander.jusbrasilprocessservice.domain.UniqueProcessNumbering;

import java.util.Map;
import java.util.Optional;

class InMemoryRetrieveProcessGateway implements RetrieveProcessGateway {

  private final Map<String, Process> processes;

  public InMemoryRetrieveProcessGateway() {
    processes = ProcessFixtureFactory.createProcesses();
  }

  @Override
  public Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering) {
    return Optional.ofNullable(processes.get(uniqueProcessNumbering.getValue()));
  }
}
