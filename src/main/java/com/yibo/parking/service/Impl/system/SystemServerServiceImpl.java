package com.yibo.parking.service.Impl.system;

import com.yibo.parking.dao.system.SystemServerMapper;
import com.yibo.parking.entity.system.SystemServer;
import com.yibo.parking.service.SystemServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServerServiceImpl implements SystemServerService {

    @Autowired
    private SystemServerMapper serverMapper;

    @Override
    public List<SystemServer> findList(SystemServer systemServer) {
        return serverMapper.findList(systemServer);
    }

    @Override
    public String insert(SystemServer systemServer) {
        return null;
    }

    @Override
    public String update(SystemServer systemServer) {
        return null;
    }

    @Override
    public String delete(SystemServer systemServer) {
        return null;
    }
}
