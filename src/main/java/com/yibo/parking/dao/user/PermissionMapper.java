package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    List<Permission> findList(Permission permission);

    Permission get(Permission permission);

    int insert(Permission permission);

    int update(Permission permission);

    int delete(Permission permission);
}
