package io.github.ricardosander.jusbrasilprocessservice.configuration.gateways;

import io.github.ricardosander.jusbrasilprocessservice.application.Process;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;

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
