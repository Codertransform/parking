package com.yibo.parking.service.Impl.user;

import com.yibo.parking.dao.user.UserMapper;
import com.yibo.parking.dao.user.UserRoleMapper;
import com.yibo.parking.entity.user.Role;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.entity.user.UserRole;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User get(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.get(user);
    }

    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    public void loginUpdate(User user) {
        userMapper.loginUpdate(user);
    }

    public Map<String,Object> save(User user){
        Map<String,Object> map = new HashMap<>();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserRole ur = new UserRole();
        //更新管理员信息
        if (user.getId() != null) {
            User u = userMapper.get(user);
            if (user.getUsername() != null && !user.getUsername().equals("")) {
                u.setUsername(user.getUsername());
            }
            if (user.getPassword() != null && !user.getPassword().equals("")){
                u.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getSex() != null) {
                u.setSex(user.getSex());
            }
            if (user.getPhone() != null && !user.getPhone().equals("")) {
                u.setPhone(user.getPhone());
            }
            if (user.getEmail() != null && !user.getPhone().equals("")) {
                u.setEmail(user.getEmail());
            }
            if (user.getRoleId() != null){
                ur.setUserId(user.getId());
                UserRole userRole = userRoleMapper.get(ur);
                userRole.setRoleId(user.getRoleId());
                userRoleMapper.update(userRole);
            }
            map.put("flag",userMapper.update(u));
            map.put("message","更新管理员信息成功");
            return map;
        }
        //新增管理员信息
        user.setId(EntityIdGenerate.generateId());
        ur.setId(EntityIdGenerate.generateId());
        ur.setUserId(user.getId());
        ur.setRoleId(user.getRoleId());
        userRoleMapper.insert(ur);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegist_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("flag",userMapper.insert(user));
        map.put("message","添加管理员信息成功");
        return map;
    }

    public int delete(User user) {
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        List<UserRole> list = userRoleMapper.findList(ur);
        for (UserRole ur1 : list) {
            userRoleMapper.delete(ur1);
        }
        return userMapper.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByName(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getFlag()));
        }
        return new org.springframework.security.core.userdetails.User(s,user.getPassword(),authorities);
    }

    public User getUser(User user) {
        return userMapper.get(user);
    }
}
