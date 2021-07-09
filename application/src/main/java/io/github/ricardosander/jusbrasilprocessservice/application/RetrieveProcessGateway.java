package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.Optional;

interface RetrieveProcessGateway {
    Optional<Process> execute(String uniqueProcessNumbering);
}
