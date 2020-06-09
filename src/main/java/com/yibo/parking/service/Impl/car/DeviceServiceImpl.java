package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.DeviceMapper;
import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.dao.system.SystemServerMapper;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.service.DeviceService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private SystemDataMapper dataMapper;

    @Autowired
    private SystemServerMapper serverMapper;

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
            return deviceMapper.update(device);
        }
//        String key = dataMapper.getByKey("高德","key");
//        SystemServer server = serverMapper.findGDByKey();
        device.setId(EntityIdGenerate.generateId());
        device.setStatus("0");
        return deviceMapper.insert(device);
    }

    @Override
    public int delete(Device device) {
        return deviceMapper.delete(device);
    }

    public Map<String,Object> status(Device device) {
        Map<String,Object> map = new HashMap<>();
        int s = deviceMapper.status(device);
        Device d = new Device();
        d.setId(device.getId());
        if (s != 0) {
            map.put("flag", true);
            map.put("device",deviceMapper.get(d));
        }else {
            map.put("flag",false);
        }
        return map;
    }
}
