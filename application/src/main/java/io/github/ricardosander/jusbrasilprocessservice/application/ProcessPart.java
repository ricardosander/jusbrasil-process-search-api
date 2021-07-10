package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.Objects;

class ProcessPart {
    private final String type;
    private final String subType;
    private final String part;

    public ProcessPart(String type, String part) {
        this.type = type;
        this.subType = null;
        this.part = part;
    }

    public ProcessPart(String type, String subType, String part) {
        this.type = type;
        this.subType = subType;
        this.part = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessPart that = (ProcessPart) o;
        return Objects.equals(type, that.type)
                && Objects.equals(subType, that.subType)
                && Objects.equals(part, that.part);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, part);
    }
}
