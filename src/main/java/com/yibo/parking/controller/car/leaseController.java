package com.yibo.parking.controller.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/lease")
public class leaseController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
//        model.addAttribute("count",count);
        model.addAttribute("title","车辆租用");
        return "hire/index";
    }
}
