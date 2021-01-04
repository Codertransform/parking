package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.device.DeviceMapper;
import com.yibo.parking.dao.car.TransformDataMapper;
import com.yibo.parking.dao.unit.UserUnitMapper;
import com.yibo.parking.dao.user.UserMapper;
import com.yibo.parking.entity.car.TransformData;
import com.yibo.parking.entity.unit.UserUnit;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.TransformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformDataServiceImpl implements TransformDataService {

    @Autowired
    private TransformDataMapper dataMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserUnitMapper userUnitMapper;

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
        List<TransformData> datas = new ArrayList<>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TransformData> dataList = dataMapper.getLocation(data);
        if (name.equals("admin")){
            return dataList;
        }
        User user = userMapper.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        UserUnit un = userUnitMapper.getByUser(user.getId());
        for (TransformData t : dataList) {
            if (t.getUnitId() != null && t.getUnitId().equals(un.getUnitId())) {
                datas.add(t);
            }
        }
        return datas;
    }
}
