package com.yibo.parking.controller.admin;

import com.yibo.parking.entity.user.Role;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.role.RoleServiceImpl;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping(value = {"","/"})
    public String index(User user, Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("title", "管理员列表");
        model.addAttribute("users", users);
        model.addAttribute("count", users.size());
        return "/admin/index";
    }

    @RequestMapping(value = "/add")
    public String add(Role role, Model model){
        List<Role> roles = roleService.findList(role);
        model.addAttribute("title","新增管理员");
        model.addAttribute("roles",roles);
        return "/admin/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(User user, Model model){
        model.addAttribute("title","修改管理员");
        return "/admin/edit";
    }
}
