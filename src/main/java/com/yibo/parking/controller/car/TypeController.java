package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Type;
import com.yibo.parking.service.Impl.car.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/type")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    @RequestMapping(value = {"","/"})
    public String index(Type type, Model model){
        model.addAttribute("title","车辆类型");
        return "index";
    }
}
