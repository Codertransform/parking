package com.yibo.parking.service.Impl.role;

import com.yibo.parking.dao.user.PermissionMapper;
import com.yibo.parking.dao.user.RoleMapper;
import com.yibo.parking.dao.user.RolePermissionMapper;
import com.yibo.parking.entity.user.Permission;
import com.yibo.parking.entity.user.Role;
import com.yibo.parking.entity.user.RolePermission;
import com.yibo.parking.service.RoleService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Role get(Role role) {
        return roleMapper.get(role);
    }

    @Override
    public List<Role> findList(Role role) {
        return roleMapper.findList(role);
    }

    @Override
    public int delete(Role role) {
        return roleMapper.delete(role);
    }

    @Override
    public Map<String, Object> save(Role role, String[] pers) {
        Map<String,Object> map = new HashMap<>();
        if (pers == null) {
            map.put("flag",0);
            map.put("message", "权限信息错误，请联系管理员！");
            return map;
        }
        if (role.getId() != null) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            List<RolePermission> rolePermissions = rolePermissionMapper.findList(rolePermission);
            if (rolePermissions != null) {
                for (RolePermission rp : rolePermissions) {
                    rolePermissionMapper.delete(rp);
                }
            }
            for (String p : pers) {
                RolePermission rolePermission1 = new RolePermission();
                rolePermission1.setId(EntityIdGenerate.generateId());
                rolePermission1.setRoleId(role.getId());
                rolePermission1.setPermissionId(p);
                rolePermissionMapper.insert(rolePermission1);
            }
            map.put("flag",roleMapper.update(role));
            map.put("message", "角色信息修改成功");
            return map;
        }
        role.setId(EntityIdGenerate.generateId());
        for (String p : pers) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(EntityIdGenerate.generateId());
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(p);
            rolePermissionMapper.insert(rolePermission);
        }
        map.put("flag",roleMapper.insert(role));
        map.put("messge","添加角色成功");
        return map;
    }

    public List<Permission> findPers() {
        return permissionMapper.findList(new Permission());
    }
}
