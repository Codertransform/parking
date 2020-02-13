package com.yibo.parking.controller.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/insurance")
public class insuController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","车辆保险");
        return "insu/index";
    }
}
