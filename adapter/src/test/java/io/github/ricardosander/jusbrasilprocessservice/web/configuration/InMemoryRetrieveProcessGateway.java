package io.github.ricardosander.jusbrasilprocessservice.web.configuration;

import io.github.ricardosander.jusbrasilprocessservice.process.Process;
import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;

import java.util.Map;
import java.util.Optional;

class InMemoryRetrieveProcessGateway implements RetrieveProcessGateway {

  private final Map<String, Process> processes;

  InMemoryRetrieveProcessGateway() {
    processes = ProcessFixtureFactory.createProcesses();
  }

  @Override
  public Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering) {
    return Optional.ofNullable(processes.get(uniqueProcessNumbering.getValue()));
  }
}
