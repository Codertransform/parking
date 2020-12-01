package com.yibo.parking.entity.work;

public class ContractorToll {
    private String id;
    private String contractorId;
    private String tollId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getTollId() {
        return tollId;
    }

    public void setTollId(String tollId) {
        this.tollId = tollId;
    }
}
