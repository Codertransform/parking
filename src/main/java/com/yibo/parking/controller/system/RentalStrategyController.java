package com.yibo.parking.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/rental/strategy")
public class RentalStrategyController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","租车策略");
        return "system/rental/strategy/index";
    }
}
