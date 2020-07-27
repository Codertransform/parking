package com.yibo.parking.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","权限管理");
        return "/permission/index";
    }
}
