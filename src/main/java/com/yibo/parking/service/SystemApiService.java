package com.yibo.parking.service;

import com.yibo.parking.entity.system.SystemApi;

import java.util.List;

public interface SystemApiService {

    List<SystemApi> findList(SystemApi systemApi);

    String insert(SystemApi systemApi);

    String update(SystemApi systemApi);

    String delete(SystemApi systemApi);
}
