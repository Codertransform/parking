package com.yibo.parking.controller.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/maintain")
public class maintainController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","车辆保养");
        return "maintain/index";
    }
}
