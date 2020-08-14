package com.yibo.parking.controller.role;

import com.yibo.parking.entity.user.Permission;
import com.yibo.parking.entity.user.Role;
import com.yibo.parking.service.Impl.role.RoleServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
        return "role/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        List<Permission> pers = roleService.findPers();
        model.addAttribute("title","添加角色");
        model.addAttribute("pers",pers);
        return "role/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Role role, Model model){
        List<Permission> pers = roleService.findPers();
        model.addAttribute("title","修改角色");
        model.addAttribute("pers",pers);
        model.addAttribute("role",roleService.get(role));
        return "role/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Role role,@RequestParam("pers[]") String[] pers){
        Map<String,Object> map = roleService.save(role,pers);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(role, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(role);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Role role){
        int d = roleService.delete(role);
        if (d != 0) {
            return JsonUtils.success(role,"删除角色成功");
        }
        return JsonUtils.error(role);
    }
}
