package com.yibo.parking.entity.device;

public class Device {
    private String id;
    private String sId;
    private String tId;
    private String deviceId;
    private String unitId;
    private String unitName;
    private String carId;
    private String cardId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", sId='" + sId + '\'' +
                ", tId='" + tId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", unitName='" + unitName + '\'' +
                ", carId='" + carId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
