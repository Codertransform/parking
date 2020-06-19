package com.yibo.parking.controller.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cars/gallery")
public class GalleryController {

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        model.addAttribute("title","车辆图库");
        return "cars/gallery/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        return "cars/gallery/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(){
        return null;
    }
}
