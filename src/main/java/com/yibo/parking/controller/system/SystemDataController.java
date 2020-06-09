package com.yibo.parking.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/data")
public class SystemDataController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","数据字典");
        return "";
    }
}
