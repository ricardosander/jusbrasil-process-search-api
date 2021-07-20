package io.github.ricardosander.jusbrasilprocessservice.web;

import io.github.ricardosander.jusbrasilprocessservice.process.Process;
import io.github.ricardosander.jusbrasilprocessservice.process.RetrieveProcessUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessSearchController {

    private final RetrieveProcessUseCase retrieveProcessUseCase;

    public ProcessSearchController(RetrieveProcessUseCase retrieveProcessUseCase) {
        this.retrieveProcessUseCase = retrieveProcessUseCase;
    }

    @GetMapping("/{processNumber}")
    public Process processSearchByProcessNumber(@PathVariable("processNumber") String processNumber) {
        return retrieveProcessUseCase.execute(processNumber)
                .orElseThrow(() -> new ProcessNotFoundException(processNumber));
    }
}
