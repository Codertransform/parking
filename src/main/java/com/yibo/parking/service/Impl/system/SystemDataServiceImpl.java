package com.yibo.parking.service.Impl.system;

import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.service.SystemDataService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataMapper dataMapper;

    @Override
    public List<SystemData> findList(SystemData data) {
        return dataMapper.findList(data);
    }

    @Override
    public SystemData get(SystemData systemData) {
        return dataMapper.get(systemData);
    }

    @Override
    public int save(SystemData systemData) {
        int s = 0;
        if (systemData.getId() != null){
            s = dataMapper.update(systemData);
        }else {
            systemData.setId(EntityIdGenerate.generateId());
            s = dataMapper.insert(systemData);
        }
        return s;
    }

    @Override
    public int delete(SystemData systemData) {
        return dataMapper.delete(systemData);
    }

    public List<SystemData> findByAd() {
        SystemData data = new SystemData();
        data.setType("address");
        return dataMapper.findList(data);
    }
}
