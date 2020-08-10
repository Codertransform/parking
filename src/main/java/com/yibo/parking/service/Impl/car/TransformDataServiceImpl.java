package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.DeviceMapper;
import com.yibo.parking.dao.car.TransformDataMapper;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.service.TransformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformDataServiceImpl implements TransformDataService {

    @Autowired
    private TransformDataMapper dataMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<String[]> findList(TransformData data) {
        List<TransformData> datas = dataMapper.findList(data);
        List<String[]> list = new ArrayList<>();
        for (TransformData d : datas) {
            String[] tds = {d.getCarId(),d.getLatitude(),d.getLongitude()};
            list.add(tds);
        }
        return list;
    }

    public List<TransformData> findList(){
        return dataMapper.findList(new TransformData());
    }

    public List<TransformData> getLocation(TransformData data) {
        List<TransformData> dataList = new ArrayList<>();
        if (data.getCarId() != null && !data.getCarId().equals("陕C·7797W")){
            Device device = new Device();
            device.setCarId(data.getCarId());
            Device d = deviceMapper.get(device);
            TransformData td = new TransformData();
            td.setDeviceId(d.getDeviceId());
            TransformData transformData = dataMapper.getLocation(td);
            transformData.setCarId(d.getCarId());
            dataList.add(transformData);
            return dataList;
        }
        List<Device> devices = deviceMapper.findList(new Device());
        for (Device d : devices) {
            if (!d.getCarId().equals("陕C·7797W")){
                TransformData td = new TransformData();
                td.setDeviceId(d.getDeviceId());
                TransformData transformData = dataMapper.getLocation(td);
                transformData.setCarId(d.getCarId());
                dataList.add(transformData);
            }
        }
        return dataList;
    }
}
