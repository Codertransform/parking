package com.yibo.parking.entity.car;

public class Car {
    private String id;              //id
    private String cardId;          //车牌号
    private String brandName;       //品牌名
    private String model;           //车型
    private String color;           //颜色
    private String typeId;         //车辆类型id
    private String typeName;         //车辆类型名称
    private String status;         //状态
    private String picStatus;      //相册状态
    private String buy_time;        //购买时间
    private String maintenance;     //上次保养时间
    private String disFlag;         //分配标识
    private String unitId;          //组织机构id
    private String unitName;        //组织机构名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(String picStatus) {
        this.picStatus = picStatus;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getDisFlag() {
        return disFlag;
    }

    public void setDisFlag(String disFlag) {
        this.disFlag = disFlag;
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

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", cardId='" + cardId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", status='" + status + '\'' +
                ", picStatus='" + picStatus + '\'' +
                ", buy_time='" + buy_time + '\'' +
                ", maintenance='" + maintenance + '\'' +
                ", disFlag='" + disFlag + '\'' +
                ", unitId='" + unitId + '\'' +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}
