package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.DeviceMapper;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.service.DeviceService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> findList(Device device) {
        return deviceMapper.findList(device);
    }

    @Override
    public Device get(Device device) {
        return deviceMapper.get(device);
    }

    @Override
    public int save(Device device) {
        if (device.getId() != null){
            System.out.println(device.getDeviceId());
            return deviceMapper.update(device);
        }
        device.setId(EntityIdGenerate.generateId());
        device.setStatus("0");
        return deviceMapper.insert(device);
    }

    @Override
    public int delete(Device device) {
        return deviceMapper.delete(device);
    }
}
