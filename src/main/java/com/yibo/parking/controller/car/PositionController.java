package com.yibo.parking.controller.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cars/position")
public class PositionController {

    @RequestMapping(value = {"","/"})
    public String index(String carId,String logmin, String logmax, Model model){
        model.addAttribute("title","车辆定位追踪");
        model.addAttribute("cardId",carId);
        model.addAttribute("logmin",logmin);
        model.addAttribute("logmax",logmax);
        return "cars/position/index";
    }


}
