package com.yibo.parking.service.Impl.car;

import com.alibaba.fastjson.JSONObject;
import com.yibo.parking.dao.car.DeviceMapper;
import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.service.DeviceService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.HttpClientUtil;
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
            return update(device);
        }
        return insert(device);
    }

    private int update(Device device){
        SystemData data = dataMapper.getByKey("高德", "key");
        String url = "https://tsapi.amap.com/v1/track/terminal/update";
        Map<String, String> map = new HashMap<>();
        map.put("key",data.getValue());
        map.put("sid",device.getsId());
        map.put("tid",device.getDeviceId());
        String result = HttpClientUtil.doPost(url,map);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("errcode").toString().equals("10000")){
            return deviceMapper.update(device);
        }
        return 0;
    }

    private int insert(Device device){
        device.setId(EntityIdGenerate.generateId());
        device.setStatus("0");
        SystemData data = dataMapper.getByKey("高德","key");
        String url = "https://tsapi.amap.com/v1/track/terminal/add";
        Map<String,String> map = new HashMap<>();
        map.put("key",data.getValue());
        map.put("sid",device.getsId());
        map.put("name",device.getDeviceId());
        String result = HttpClientUtil.doPost(url,map);
        JSONObject object = JSONObject.parseObject(result);
        if (object.get("errcode").toString().equals("10000")){
            JSONObject json = object.getJSONObject("data");
            device.settId(json.get("tid").toString());
            return deviceMapper.insert(device);
        }
        return 0;
    }

    @Override
    public int delete(Device device) {
        device = deviceMapper.get(device);
        SystemData data = dataMapper.getByKey("高德","key");
        String url = "https://tsapi.amap.com/v1/track/terminal/delete";
        Map<String,String> map = new HashMap<>();
        map.put("key",data.getValue());
        map.put("sid",device.getsId());
        map.put("tid",device.gettId());
        String result = HttpClientUtil.doPost(url,map);
        JSONObject object = JSONObject.parseObject(result);
        System.out.println(object);
        if (object.get("errcode").toString().equals("10000")){
            return deviceMapper.delete(device);
        }
        return 0;
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
