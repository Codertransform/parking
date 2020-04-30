package com.yibo.parking.service;

import com.yibo.parking.entity.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User get(String username, String password);

    User findByName(String username);

    int update(User user);

    int loginUpdate(User user);

    int insert(User user);

    int delete(User user);
}
