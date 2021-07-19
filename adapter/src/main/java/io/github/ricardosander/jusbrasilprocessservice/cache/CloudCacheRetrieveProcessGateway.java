package io.github.ricardosander.jusbrasilprocessservice.cache;

import io.github.ricardosander.jusbrasilprocessservice.application.Process;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Optional;

public class CloudCacheRetrieveProcessGateway implements RetrieveProcessGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudCacheRetrieveProcessGateway.class);

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
        LOGGER.info("Searching process {} in cloud cache", uniqueProcessNumbering.getValue());
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
        LOGGER.info("Putting process {} in cloud cache", uniqueProcessNumbering.getValue());
        this.cache.put(uniqueProcessNumbering.getValue(), process);
        return process;
    }
}
