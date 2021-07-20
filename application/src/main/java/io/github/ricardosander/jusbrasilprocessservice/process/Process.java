package io.github.ricardosander.jusbrasilprocessservice.process;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Process implements Serializable {
    private final String clazz;
    private final String area;
    private final String subject;
    private final DistributionDate distributionDate;
    private final String judge;
    private final String shareValue;
    private final List<ProcessPart> processParts;
    private final List<Movement> movements;

    public static class Builder {

        private String clazz;
        private String area;
        private String subject;
        private DistributionDate distributionDate;
        private String judge;
        private String shareValue;
        private final List<ProcessPart> processParts;
        private final List<Movement> movements;

        private Builder() {
            this.processParts = new ArrayList<>();
            this.movements = new ArrayList<>();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder clazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder area(String area) {
            this.area = area;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder distributionDate(LocalDateTime distributionDate, String distributionDateType) {
            this.distributionDate = new DistributionDate(distributionDate, distributionDateType);
            return this;
        }

        public Builder judge(String judge) {
            this.judge = judge;
            return this;
        }

        public Builder shareValue(String shareValue) {
            this.shareValue = shareValue;
            return this;
        }

        public Builder processParts(String type, String part) {
            this.processParts.add(new ProcessPart(type, part));
            return this;
        }

        public Builder processParts(String type, String subType, String part) {
            this.processParts.add(new ProcessPart(type, subType, part));
            return this;
        }

        public Builder movements(LocalDate date, String description) {
            this.movements.add(new Movement(date, description));
            return this;
        }

        public Process build() {
            return new Process(
              this.clazz,
              this.area,
              this.subject,
              this.distributionDate,
              this.judge,
              this.shareValue,
              this.processParts,
              this.movements
            );
        }
    }
}
