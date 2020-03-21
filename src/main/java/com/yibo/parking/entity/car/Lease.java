package com.yibo.parking.entity.car;

public class Lease {
    private String id;
    private String carId;
    private String unit;
    private String type;
    private String startmiles;
    private String endmiles;
    private String position;
    private String status;
    private String startdate;
    private String enddate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartmiles() {
        return startmiles;
    }

    public void setStartmiles(String startmiles) {
        this.startmiles = startmiles;
    }

    public String getEndmiles() {
        return endmiles;
    }

    public void setEndmiles(String endmiles) {
        this.endmiles = endmiles;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
