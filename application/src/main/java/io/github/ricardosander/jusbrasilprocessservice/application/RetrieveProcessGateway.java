package io.github.ricardosander.jusbrasilprocessservice.application;

import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;

import java.util.Optional;

public interface RetrieveProcessGateway {
    Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering);
}
