package com.yibo.parking.entity.car;

public class Type {
    private String id;
    private String name;
    private int hour;
    private int halfday;
    private int allday;
    private int week;
    private int month;
    private int halfyear;

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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHalfday() {
        return halfday;
    }

    public void setHalfday(int halfday) {
        this.halfday = halfday;
    }

    public int getAllday() {
        return allday;
    }

    public void setAllday(int allday) {
        this.allday = allday;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHalfyear() {
        return halfyear;
    }

    public void setHalfyear(int halfyear) {
        this.halfyear = halfyear;
    }
}
