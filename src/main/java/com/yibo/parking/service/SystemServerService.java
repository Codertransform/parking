package com.yibo.parking.service;

import com.yibo.parking.entity.system.SystemServer;

import java.util.List;

public interface SystemServerService {

    List<SystemServer> findList(SystemServer systemServer);

    String insert(SystemServer systemServer);

    String update(SystemServer systemServer);

    String delete(SystemServer systemServer);
}
