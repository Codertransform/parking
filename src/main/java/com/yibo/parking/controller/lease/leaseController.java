package com.yibo.parking.controller.lease;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/lease")
public class leaseController {

    @RequestMapping(value = "/")
    public String index(Model model){
//        model.addAttribute("lease",lease);
        return "index";
    }
}
