package com.yibo.parking.service;

import com.yibo.parking.entity.device.Device;

import java.util.List;

public interface DeviceService {

    List<Device> findList(Device device);

    Device get(Device device);

    int save(Device device);

    int delete(Device device);
}
