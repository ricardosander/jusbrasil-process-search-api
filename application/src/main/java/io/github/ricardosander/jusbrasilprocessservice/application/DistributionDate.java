package io.github.ricardosander.jusbrasilprocessservice.application;

import java.time.LocalDateTime;

class DistributionDate {

    private final LocalDateTime data;
    private final String type;

    public DistributionDate(LocalDateTime data, String type) {
        this.data = data;
        this.type = type;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getType() {
        return type;
    }
}
