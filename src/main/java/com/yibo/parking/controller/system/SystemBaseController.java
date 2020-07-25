package com.yibo.parking.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/base")
public class SystemBaseController {


    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","系统设置");
        return "/system/base/index";
    }
}
