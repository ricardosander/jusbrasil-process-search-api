package io.github.ricardosander.jusbrasilprocessservice.application;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionDate that = (DistributionDate) o;
        return Objects.equals(data, that.data) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, type);
    }
}
