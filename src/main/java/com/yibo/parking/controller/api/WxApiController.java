package com.yibo.parking.controller.api;

import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.wx.WxBannerServiceImpl;
import com.yibo.parking.service.Impl.wx.WxMenusServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ResponseBody
    @RequestMapping(value = "/getCarInfo")
    public String getCarInfo(@RequestParam("id") String id){
        System.out.println(carService.get(id));
        return JsonUtils.success(carService.get(id),"成功获取车辆信息");
    }
}
