package com.yibo.parking.service.Impl.user;

import com.yibo.parking.dao.user.PermissionMapper;
import com.yibo.parking.entity.user.Permission;
import com.yibo.parking.service.PermissionService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findList(Permission permission) {
        return permissionMapper.findList(permission);
    }

    @Override
    public Permission get(Permission permission) {
        return permissionMapper.get(permission);
    }

    @Override
    public Map<String, Object> save(Permission permission) {
        Map<String, Object> map = new HashMap<>();
        if (permission.getId() != null){
            map.put("flag",permissionMapper.update(permission));
            map.put("message","权限节点信息修改成功");
            return map;
        }
        permission.setId(EntityIdGenerate.generateId());
        map.put("flag",permissionMapper.insert(permission));
        map.put("message","权限节点信息添加成功");
        return map;
    }

    @Override
    public int delete(Permission permission) {
        return permissionMapper.delete(permission);
    }
}
