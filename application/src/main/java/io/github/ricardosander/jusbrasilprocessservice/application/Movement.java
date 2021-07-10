package io.github.ricardosander.jusbrasilprocessservice.application;

import java.time.LocalDate;
import java.util.Objects;

class Movement {
    private final LocalDate date;
    private final String description;

    Movement(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(date, movement.date) && Objects.equals(description, movement.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description);
    }
}
