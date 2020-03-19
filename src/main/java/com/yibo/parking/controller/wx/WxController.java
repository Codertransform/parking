package com.yibo.parking.controller.wx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/wx")
public class WxController {

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(String code){
        System.out.println(code);
        return "123";
    }
}
