package com.yibo.parking.service.Impl.user;

import com.yibo.parking.dao.unit.UserUnitMapper;
import com.yibo.parking.dao.user.UserMapper;
import com.yibo.parking.dao.user.UserRoleMapper;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.entity.unit.UserUnit;
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

    @Autowired
    private UserUnitMapper userUnitMapper;

    public List<User> getAllUser() {
        List<User> users = userMapper.getAllUser();
        for (User u : users) {
            Unit unit = u.getUnit();
            if (unit == null) {
                unit = new Unit();
                unit.setName("无所属机构");
                u.setUnit(unit);
            }
        }
        return users;
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
            if (user.getRoleId() != null){
                ur.setUserId(user.getId());
                UserRole userRole = userRoleMapper.get(ur);
                userRole.setRoleId(user.getRoleId());
                userRoleMapper.update(userRole);
            }
            UserUnit unit = userUnitMapper.getByUser(user.getId());
            if (user.getUnit() != null && user.getUnit().getId() != null && !user.getUnit().getId().equals("")){
                if (unit != null){
                    unit.setUnitId(user.getUnit().getId());
                    userUnitMapper.update(unit);
                }else {
                    unit = new UserUnit();
                    unit.setId(EntityIdGenerate.generateId());
                    unit.setUserId(user.getId());
                    unit.setUnitId(user.getUnit().getId());
                    userUnitMapper.insert(unit);
                }
            }
            user.setPassword(u.getPassword());
            map.put("flag",userMapper.update(user));
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
        UserUnit unit = new UserUnit();
        unit.setId(EntityIdGenerate.generateId());
        unit.setUserId(user.getId());
        if (user.getUnit() != null && user.getUnit().getId() != null && !user.getUnit().getId().equals("")) {
            unit .setUnitId(user.getUnit().getId());
        }else {
            unit.setUnitId("");
        }
        userUnitMapper.insert(unit);
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
        User u = userMapper.get(user);
        for (Role r : u.getRoles()) {
            u.setRoleId(r.getId());
            u.setRoleName(r.getName());
        }
        Unit unit = u.getUnit();
        if (unit == null) {
            unit = new Unit();
            unit.setId("");
            u.setUnit(unit);
        }
        return u;
    }
}
