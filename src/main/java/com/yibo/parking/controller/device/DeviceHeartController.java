package com.yibo.parking.controller.device;

import com.yibo.parking.entity.device.Heart;
import com.yibo.parking.service.Impl.device.HeartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/device/heart")
public class DeviceHeartController {

    @Autowired
    private HeartServiceImpl heartService;

    @RequestMapping(value = {"","/"})
    public String index(Heart heart, Model model){
        List<Heart> hearts = heartService.findList(heart);
        model.addAttribute("title", "设备心跳数据");
        model.addAttribute("count", hearts.size());
        model.addAttribute("hearts", hearts);
        model.addAttribute("heart", heart);
        return "device/heart/index";
    }
}
