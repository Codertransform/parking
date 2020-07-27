package com.yibo.parking.service.Impl.role;

import com.yibo.parking.dao.user.RoleMapper;
import com.yibo.parking.entity.user.Role;
import com.yibo.parking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role get(Role role) {
        return roleMapper.get(role);
    }

    @Override
    public List<Role> findList(Role role) {
        return roleMapper.findList(role);
    }

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int delete(Role role) {
        return roleMapper.delete(role);
    }
}
