package com.yibo.parking.dao.user;

import com.yibo.parking.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser();

    User get(User user);

    User findByName(@Param("username") String username);

    int update(User user);

    int loginUpdate(User user);

    int insert(User user);

    int delete(User user);
}
