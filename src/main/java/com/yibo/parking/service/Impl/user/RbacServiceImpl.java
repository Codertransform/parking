package com.yibo.parking.service.Impl.user;

import com.yibo.parking.dao.user.RoleMapper;
import com.yibo.parking.dao.user.UserMapper;
import com.yibo.parking.entity.user.Permission;
import com.yibo.parking.entity.user.Role;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            String userName = ((UserDetails) principal).getUsername();
            User user = userMapper.findByName(userName);
            List<Role> roles = user.getRoles();
            List<Permission> permissions = new ArrayList<>();
            for (Role r : roles) {
                r= roleMapper.get(r);
                if (r.getFlag().equals("ROLE_ADMIN")) {
                    return true;
                }
                permissions.addAll(r.getPermissions());
            }
            Set<String> urls = new HashSet<>(); // 数据库读取 //读取用户所拥有权限的所有URL
            for (Permission p : permissions) {
                urls.add(p.getUrl());
            }
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
