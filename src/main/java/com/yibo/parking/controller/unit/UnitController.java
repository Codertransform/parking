package com.yibo.parking.controller.unit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/unit")
public class UnitController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        return "unit/index";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "unit/add";
    }
}
