package com.yibo.parking.controller.wx;

import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.utils.HttpURLConnectionDemo;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class WxApiController {

    @Autowired
    private CarServiceImpl carService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(String code){
        String code2Session = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        String url = code2Session.replace("APPID","wxdc735b2116d0763e")
                .replace("SECRET","7d47726502ee6f9d4b35ec2a1ac07fa7")
                .replace("JSCODE",code);
        String userInfo = HttpURLConnectionDemo.doGet(url);
        System.out.println(userInfo);
        return JsonUtils.success(code,"code码申请成功");
    }

    @ResponseBody
    @RequestMapping(value = "/getCarsInfo")
    public String getCarsInfo(){
        return JsonUtils.success(carService.getByStatus(),"获取到所有车辆");
    }

    @ResponseBody
    @RequestMapping(value = "/getBanners")
    public String getBanners(){
        return JsonUtils.success("123","成功获取所有banner");
    }
}
