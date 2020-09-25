package com.yibo.parking.controller.admin;

import com.yibo.parking.entity.user.Role;
import com.yibo.parking.entity.user.User;
import com.yibo.parking.service.Impl.role.RoleServiceImpl;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import com.yibo.parking.service.Impl.user.UserServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private UnitServiceIpml unitService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("title", "管理员列表");
        model.addAttribute("users", users);
        model.addAttribute("count", users.size());
        return "admin/index";
    }

    @RequestMapping(value = "/add")
    public String add(Role role, Model model){
        List<Role> roles = roleService.findList(role);
        model.addAttribute("title","新增管理员");
        model.addAttribute("roles",roles);
        return "admin/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(User user, Model model){
        model.addAttribute("title","修改管理员");
        model.addAttribute("roles",roleService.findList(new Role()));
        model.addAttribute("units",unitService.findList());
        model.addAttribute("admin",userService.getUser(user));
        return "admin/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(User user){
        Map<String,Object> map = userService.save(user);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(user, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(user);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(User user){
        int d = userService.delete(user);
        if (d != 0) {
            return JsonUtils.success(user,"用户删除成功");
        }
        return JsonUtils.error(user);
    }
}
