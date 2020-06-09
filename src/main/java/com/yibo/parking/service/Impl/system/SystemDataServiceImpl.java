package com.yibo.parking.service.Impl.system;

import com.yibo.parking.dao.system.SystemDataMapper;
import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataMapper dataMapper;

    @Override
    public List<SystemData> findList(SystemData data) {
        return null;
    }

    @Override
    public SystemData get(SystemData systemData) {
        return null;
    }

    @Override
    public int save(SystemData systemData) {
        return 0;
    }

    @Override
    public int delete(SystemData systemData) {
        return 0;
    }
}
