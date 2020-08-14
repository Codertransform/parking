package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    List<UserRole> findList(UserRole userRole);

    UserRole get(UserRole userRole);

    int insert(UserRole userRole);

    int update(UserRole userRole);

    int delete(UserRole userRole);
}
