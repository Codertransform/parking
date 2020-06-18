package com.yibo.parking.entity.util;

import java.io.Serializable;

public class Json implements Serializable {
    private String code;
    private Object data;
    private String message;
    private Integer all;
    private Integer unpay;
    private Integer paied;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getUnpay() {
        return unpay;
    }

    public void setUnpay(Integer unpay) {
        this.unpay = unpay;
    }

    public Integer getPaied() {
        return paied;
    }

    public void setPaied(Integer paied) {
        this.paied = paied;
    }
}
