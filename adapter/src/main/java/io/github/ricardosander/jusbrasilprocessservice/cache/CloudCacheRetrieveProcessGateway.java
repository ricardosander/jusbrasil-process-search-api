package io.github.ricardosander.jusbrasilprocessservice.cache;

import io.github.ricardosander.jusbrasilprocessservice.application.Process;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Optional;

public class CloudCacheRetrieveProcessGateway implements RetrieveProcessGateway {

    private static final String CACHE_NAME = "PROCESS";

    private final RetrieveProcessGateway retrieveProcessGateway;
    private final Cache cache;

    public CloudCacheRetrieveProcessGateway(
            RetrieveProcessGateway retrieveProcessGateway,
            CacheManager cacheManager
    ) {
        this.retrieveProcessGateway = retrieveProcessGateway;
        this.cache = cacheManager.getCache(CACHE_NAME);
    }

    @Override
    public Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering) {
        return Optional.ofNullable(retrieveFromCache(uniqueProcessNumbering))
                .or(() -> retrieveFromGateway(uniqueProcessNumbering)
                .map(process -> putOnCache(uniqueProcessNumbering, process)));
    }

    private Process retrieveFromCache(UniqueProcessNumbering uniqueProcessNumbering) {
        return this.cache.get(uniqueProcessNumbering.getValue(), Process.class);
    }

    private Optional<Process> retrieveFromGateway(UniqueProcessNumbering uniqueProcessNumbering) {
        return retrieveProcessGateway.execute(uniqueProcessNumbering);
    }

    private Process putOnCache(UniqueProcessNumbering uniqueProcessNumbering, Process process) {
        this.cache.put(uniqueProcessNumbering.getValue(), process);
        return process;
    }
}
