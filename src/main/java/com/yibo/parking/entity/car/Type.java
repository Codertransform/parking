package com.yibo.parking.entity.car;

import java.util.List;

public class Type {
    private String id;
    private String name;
    private List<TypeInfo> infos;
    private Integer hour;
    private Integer halfday;
    private Integer allday;
    private Integer week;
    private Integer month;
    private Integer halfyear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<TypeInfo> infos) {
        this.infos = infos;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getHalfday() {
        return halfday;
    }

    public void setHalfday(Integer halfday) {
        this.halfday = halfday;
    }

    public Integer getAllday() {
        return allday;
    }

    public void setAllday(Integer allday) {
        this.allday = allday;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getHalfyear() {
        return halfyear;
    }

    public void setHalfyear(Integer halfyear) {
        this.halfyear = halfyear;
    }
}
