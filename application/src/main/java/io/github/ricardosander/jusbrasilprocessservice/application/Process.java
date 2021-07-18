package io.github.ricardosander.jusbrasilprocessservice.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Process {
    private final String clazz;
    private final String area;
    private final String subject;
    private final DistributionDate distributionDate;
    private final String judge;
    private final String shareValue;
    private final List<ProcessPart> processParts;
    private final List<Movement> movements;
}
