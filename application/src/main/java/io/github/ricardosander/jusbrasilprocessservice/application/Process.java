package io.github.ricardosander.jusbrasilprocessservice.application;

public class Process {

    private final String id;
    private final String clazz;
    private final String area;
    private final String subject;
    private final String distributionDate;
    private final String judge;
    private final String shareValue;
    private final String processParts;
    private final String movements;

    public Process(
            String id,
            String clazz,
            String area,
            String subject,
            String distributionDate,
            String judge,
            String shareValue,
            String processParts,
            String movements
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

    public String getDistributionDate() {
        return distributionDate;
    }

    public String getJudge() {
        return judge;
    }

    public String getShareValue() {
        return shareValue;
    }

    public String getProcessParts() {
        return processParts;
    }

    public String getMovements() {
        return movements;
    }
}
