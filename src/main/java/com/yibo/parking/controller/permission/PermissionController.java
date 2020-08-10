package com.yibo.parking.controller.permission;

import com.yibo.parking.entity.user.Permission;
import com.yibo.parking.service.Impl.user.PermissionServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

    @Autowired
    private PermissionServiceImpl permissionService;

    @RequestMapping(value = {"","/"})
    public String index(Permission permission, Model model){
        List<Permission> pers = permissionService.findList(permission);
        model.addAttribute("title","权限节点管理");
        model.addAttribute("pers",pers);
        model.addAttribute("per",permission);
        model.addAttribute("count",pers.size());
        return "/permission/index";
    }

    @RequestMapping(value = "/add")
    public String add(Permission permission, Model model){
        List<Permission> pers = permissionService.findList(permission);
        model.addAttribute("permission", pers);
        return "/permission/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Permission permission, Model model){
        permission = permissionService.get(permission);
        List<Permission> pers = permissionService.findList(new Permission());
        model.addAttribute("permission",permission);
        model.addAttribute("pers",pers);
        return "/permission/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Permission permission){
        Map<String, Object> map = permissionService.save(permission);
        int flag = (int) map.get("flag");
        if (flag != 0){
            return JsonUtils.success(permission, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(permission);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String del(Permission permission){
        return JsonUtils.success(permissionService.delete(permission),"删除成功");
    }
}
