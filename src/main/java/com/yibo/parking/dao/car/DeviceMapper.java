package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {
    List<Device> findList(Device device);

    Device get(Device device);

    Device getByDeviceId(Device device);

    int insert(Device device);

    int update(Device device);

    int delete(Device device);

    int status(Device device);
}
