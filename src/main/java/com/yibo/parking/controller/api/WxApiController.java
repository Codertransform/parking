package com.yibo.parking.controller.api;

import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.wx.WxBannerServiceImpl;
import com.yibo.parking.service.Impl.wx.WxMenusServiceImpl;
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

    @Autowired
    private WxBannerServiceImpl bannerService;

    @Autowired
    private WxMenusServiceImpl menusService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(String code){
        String code2Session = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        String url = code2Session.replace("APPID","wxdc735b2116d0763e")
                .replace("SECRET","7d47726502ee6f9d4b35ec2a1ac07fa7")
                .replace("JSCODE",code);
        String userInfo = HttpURLConnectionDemo.doGet(url);
        if (!userInfo.equals("")) {
            return JsonUtils.success(userInfo,"获取用户凭证成功");
        }
        return JsonUtils.error(userInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/getCarsInfo")
    public String getCarsInfo(){
        return JsonUtils.success(carService.getByStatus(),"获取到所有车辆");
    }

    @ResponseBody
    @RequestMapping(value = "/getBanners")
    public String getBanners(){
        return JsonUtils.success(bannerService.getBannersApi(),"成功获取所有可用banner");
    }

    @ResponseBody
    @RequestMapping(value = "/getMenus")
    public String getMenus(){
        String menus = menusService.findByStatusApi();
        if (menus != null){
            return JsonUtils.success(menus,"成功获取功能菜单");
        }
        return JsonUtils.error(menus);
    }
}
