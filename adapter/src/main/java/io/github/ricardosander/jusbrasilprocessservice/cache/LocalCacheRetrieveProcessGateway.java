package io.github.ricardosander.jusbrasilprocessservice.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.ricardosander.jusbrasilprocessservice.application.Process;
import io.github.ricardosander.jusbrasilprocessservice.application.RetrieveProcessGateway;
import io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering.UniqueProcessNumbering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LocalCacheRetrieveProcessGateway implements RetrieveProcessGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCacheRetrieveProcessGateway.class);

    private final Cache<String, Optional<Process>> cache;

    private final RetrieveProcessGateway retrieveProcessGateway;

    public LocalCacheRetrieveProcessGateway(
            LocalCacheConfigurationProperties configuration,
            RetrieveProcessGateway retrieveProcessGateway
    ) {
        this.retrieveProcessGateway = retrieveProcessGateway;

        this.cache = CacheBuilder.newBuilder()
                .maximumSize(configuration.getMaximumSize())
                .expireAfterWrite(configuration.getTtlInMinutes(), TimeUnit.MINUTES)
                .build();
    }

    @Override
    public Optional<Process> execute(UniqueProcessNumbering uniqueProcessNumbering) {
        try {
            return this.cache.get(uniqueProcessNumbering.getValue(), () -> retrieveProcessGateway.execute(uniqueProcessNumbering));
        } catch (ExecutionException e) {
            LOGGER.error("Error on local cache: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
