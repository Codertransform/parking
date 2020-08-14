package com.yibo.parking.service;

import com.yibo.parking.entity.user.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role get(Role role);

    List<Role> findList(Role role);

    Map<String,Object> save(Role role, String[] pers);

    int delete(Role role);
}
