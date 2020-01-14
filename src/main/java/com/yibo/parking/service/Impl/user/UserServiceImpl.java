package com.yibo.parking.service.Impl.user;

import com.yibo.parking.dao.user.UserMapper;
import com.yibo.parking.entity.user.User;
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
