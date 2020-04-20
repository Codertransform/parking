package com.yibo.parking.service;

import com.yibo.parking.entity.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User get(String username, String password);
}
