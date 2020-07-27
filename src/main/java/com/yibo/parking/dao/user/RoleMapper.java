package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role get(Role role);

    List<Role> findList(Role role);

    int insert(Role role);

    int update(Role role);

    int delete(Role role);
}
