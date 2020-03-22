package com.yibo.parking.entity.car;

import com.yibo.parking.entity.unit.Unit;

public class Lease {
    private String id;
    private String orderId;
    private Car car;
    private Unit unit;
    private Type type;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
