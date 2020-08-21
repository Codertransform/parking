package com.yibo.parking.entity.car;

import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.entity.user.User;

public class Dispatch {
    private String id;
    private Car car;
    private Unit unit;
    private User user;
    private String oprateTime;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOprateTime() {
        return oprateTime;
    }

    public void setOprateTime(String oprateTime) {
        this.oprateTime = oprateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
