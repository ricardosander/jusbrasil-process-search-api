package io.github.ricardosander.jusbrasilprocessservice.application;

import java.util.List;
import java.util.Objects;

class Process {

    private final String id;
    private final String clazz;
    private final String area;
    private final String subject;
    private final DistributionDate distributionDate;
    private final String judge;
    private final String shareValue;
    private final List<ProcessPart> processParts;
    private final List<String> movements;

    public Process(
            String id,
            String clazz,
            String area,
            String subject,
            DistributionDate distributionDate,
            String judge,
            String shareValue,
            List<ProcessPart> processParts,
            List<String> movements
    ) {
        this.id = id;
        this.clazz = clazz;
        this.area = area;
        this.subject = subject;
        this.distributionDate = distributionDate;
        this.judge = judge;
        this.shareValue = shareValue;
        this.processParts = processParts;
        this.movements = movements;
    }

    public String getId() {
        return id;
    }

    public String getClazz() {
        return clazz;
    }

    public String getArea() {
        return area;
    }

    public String getSubject() {
        return subject;
    }

    public DistributionDate getDistributionDate() {
        return distributionDate;
    }

    public String getJudge() {
        return judge;
    }

    public String getShareValue() {
        return shareValue;
    }

    public List<ProcessPart> getProcessParts() {
        return processParts;
    }

    public List<String> getMovements() {
        return movements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Objects.equals(id, process.id)
                && Objects.equals(clazz, process.clazz)
                && Objects.equals(area, process.area)
                && Objects.equals(subject, process.subject)
                && Objects.equals(distributionDate, process.distributionDate)
                && Objects.equals(judge, process.judge)
                && Objects.equals(shareValue, process.shareValue)
                && Objects.equals(processParts, process.processParts)
                && Objects.equals(movements, process.movements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clazz, area, subject, distributionDate, judge, shareValue, processParts, movements);
    }
}
