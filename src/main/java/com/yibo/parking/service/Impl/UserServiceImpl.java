package com.yibo.parking.service.Impl;

import com.yibo.parking.dao.UserMapper;
import com.yibo.parking.entity.User;
import com.yibo.parking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

}
