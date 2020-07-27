package com.yibo.parking.service;

import com.yibo.parking.entity.user.Role;

import java.util.List;

public interface RoleService {

    Role get(Role role);

    List<Role> findList(Role role);

    int insert(Role role);

    int update(Role role);

    int delete(Role role);
}
