package com.yibo.parking.entity.car;

public class TransformData {
    private String id;
    private String originId;        //原始数据id
    private String latitude;        //纬度
    private String longitude;      //经度
    private String carId;           //车牌号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "TransformData{" +
                "id='" + id + '\'' +
                ", originId='" + originId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", carId='" + carId + '\'' +
                '}';
    }
}
