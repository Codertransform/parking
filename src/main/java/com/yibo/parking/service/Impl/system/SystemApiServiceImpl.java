package com.yibo.parking.service.Impl.system;

import com.yibo.parking.dao.system.SystemApiMapper;
import com.yibo.parking.entity.system.SystemApi;
import com.yibo.parking.service.SystemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemApiServiceImpl implements SystemApiService {

    @Autowired
    private SystemApiMapper apiMapper;

    @Override
    public List<SystemApi> findList(SystemApi systemApi) {
        return apiMapper.findList(systemApi);
    }

    @Override
    public String insert(SystemApi systemApi) {
        return null;
    }

    @Override
    public String update(SystemApi systemApi) {
        return null;
    }

    @Override
    public String delete(SystemApi systemApi) {
        return null;
    }
}
