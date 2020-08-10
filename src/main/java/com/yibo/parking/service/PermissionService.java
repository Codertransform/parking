package com.yibo.parking.service;

import com.yibo.parking.entity.user.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    public List<Permission> findList(Permission permission);

    public Permission get(Permission permission);

    Map<String, Object> save(Permission permission);

    public int delete(Permission permission);
}
