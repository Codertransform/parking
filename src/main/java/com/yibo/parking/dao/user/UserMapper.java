package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser();

    User get(User user);
}
