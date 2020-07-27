package com.yibo.parking.controller.role;

import com.yibo.parking.entity.user.Role;
import com.yibo.parking.service.Impl.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping(value = {"","/"})
    public String index(Role role, Model model){
        List<Role> roles = roleService.findList(role);
        model.addAttribute("title","角色管理");
        model.addAttribute("roles",roles);
        model.addAttribute("count",roles.size());
        return "/role/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("ttile","添加角色");
        return "/role/add";
    }
}
