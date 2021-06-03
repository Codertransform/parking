package com.yibo.parking.controller.SDKDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/video")
public class ZegoWebController {

    @RequestMapping(value = {"","/"})
    public String index(){
        return "Zego/index";
    }
}
