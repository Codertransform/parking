package com.yibo.parking.controller.api;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.TypeInfo;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.entity.member.MemberWxInfo;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.TypeServiceImpl;
import com.yibo.parking.service.Impl.member.MemberServiceImpl;
import com.yibo.parking.service.Impl.member.MemberWxInfoServiceImpl;
import com.yibo.parking.service.Impl.wx.WxBannerServiceImpl;
import com.yibo.parking.service.Impl.wx.WxMenusServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class WxApiController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private WxBannerServiceImpl bannerService;

    @Autowired
    private WxMenusServiceImpl menusService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberWxInfoServiceImpl infoService;

    @Autowired
    private TypeServiceImpl typeService;

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
        Car car = carService.get(id);
        if (car != null){
            return JsonUtils.success(car,"成功获取车辆信息");
        }
        return JsonUtils.error(car);
    }

    @ResponseBody
    @RequestMapping(value = "/certify")
    public String certify(String skey, String name, String tel, String idCrad){
        Member member = memberService.findMember(name,tel,idCrad);
        if (member == null){
            return JsonUtils.error(member);
        }
        MemberWxInfo info = infoService.findBySkey(skey);
        if (info == null){
            return JsonUtils.error(info);
        }
        int m = memberService.certify(member, info.getOpenId());
        int i = infoService.certify(info, member.getId());
        if (m != 0 && i != 0){
            return JsonUtils.success(null,"认证成功");
        }
        return JsonUtils.error(null);
    }

    @ResponseBody
    @RequestMapping(value = "/getTypeInfos")
    public String getTypeInfos(String id){
        List<TypeInfo> infos = typeService.getTypeInfos(id);
        if (infos != null){
            return JsonUtils.success(infos,"成功获取类型明细");
        }
        return JsonUtils.error(infos);
    }
}
