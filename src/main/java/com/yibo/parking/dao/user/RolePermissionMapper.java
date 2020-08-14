package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {

    List<RolePermission> findList(RolePermission rolePermission);

    int insert(RolePermission rolePermission);

    int delete(RolePermission rolePermission);

}
