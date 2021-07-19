package io.github.ricardosander.jusbrasilprocessservice.application;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
class ProcessPart implements Serializable {
    private final String type;
    private final String subType;
    private final String part;

    public ProcessPart(String type, String part) {
        this.type = type;
        this.subType = null;
        this.part = part;
    }
}
