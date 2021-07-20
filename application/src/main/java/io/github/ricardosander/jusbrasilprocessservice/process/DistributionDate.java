package io.github.ricardosander.jusbrasilprocessservice.process;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
class DistributionDate implements Serializable {
    private final LocalDateTime date;
    private final String type;
}
