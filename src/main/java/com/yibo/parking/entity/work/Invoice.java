package com.yibo.parking.entity.work;

public class Invoice {
    private String id;
    private String numStart;
    private String numEnd;
    private String code;
    private String denomination;
    private String inTime;
    private String outTime;
    private String outer;
    private String writeinTime;
    private String status;
    private String remarks;

    private Integer step;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumStart() {
        return numStart;
    }

    public void setNumStart(String numStart) {
        this.numStart = numStart;
    }

    public String getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(String numEnd) {
        this.numEnd = numEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getOuter() {
        return outer;
    }

    public void setOuter(String outer) {
        this.outer = outer;
    }

    public String getWriteinTime() {
        return writeinTime;
    }

    public void setWriteinTime(String writeinTime) {
        this.writeinTime = writeinTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", numStart='" + numStart + '\'' +
                ", numEnd='" + numEnd + '\'' +
                ", code='" + code + '\'' +
                ", denomination='" + denomination + '\'' +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", outer='" + outer + '\'' +
                ", writeinTime='" + writeinTime + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", step=" + step +
                '}';
    }
}
