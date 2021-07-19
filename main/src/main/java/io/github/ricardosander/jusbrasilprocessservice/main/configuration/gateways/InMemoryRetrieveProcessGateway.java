package io.github.ricardosander.jusbrasilprocessservice.main.configuration.gateways;

import io.github.ricardosander.jusbrasilprocessservice.application.Process;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

class InMemoryRetrieveProcessGateway implements RetrieveProcessGateway {

  private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryRetrieveProcessGateway.class);

  private final Map<String, Process> processes;

  public InMemoryRetrieveProcessGateway() {
    processes = ProcessFixtureFactory.createProcesses();
  }

  @Override
  public Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering) {
    LOGGER.info("Searching process {} in memory", uniqueProcessNumbering.getValue());
    return Optional.ofNullable(processes.get(uniqueProcessNumbering.getValue()));
  }
}
