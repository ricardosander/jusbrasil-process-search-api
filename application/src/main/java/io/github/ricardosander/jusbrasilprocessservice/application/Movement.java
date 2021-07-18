package io.github.ricardosander.jusbrasilprocessservice.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
class Movement {
    private final LocalDate date;
    private final String description;
}
