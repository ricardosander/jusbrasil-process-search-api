package io.github.ricardosander.jusbrasilprocessservice.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
class Movement implements Serializable {
    private final LocalDate date;
    private final String description;
}
