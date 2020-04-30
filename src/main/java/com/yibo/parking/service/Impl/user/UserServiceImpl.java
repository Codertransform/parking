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

    @Override
    public User get(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.get(user);
    }

    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int loginUpdate(User user) {
        return userMapper.loginUpdate(user);
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
