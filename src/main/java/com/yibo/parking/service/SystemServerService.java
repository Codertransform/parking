package com.yibo.parking.service;

import com.yibo.parking.entity.system.SystemServer;

import java.util.List;

public interface SystemServerService {

    List<SystemServer> findList(SystemServer systemServer);

    int insert(SystemServer systemServer) throws Exception;

    int update(SystemServer systemServer);

    int delete(SystemServer systemServer);
}
