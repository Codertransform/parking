package com.yibo.parking.controller.wx;

import com.yibo.parking.entity.wx.Banner;
import com.yibo.parking.service.Impl.wx.WxBannerServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/wx/banner")
public class WxBannerController {

    @Autowired
    private WxBannerServiceImpl bannerService;

    @RequestMapping(value = {"","/"})
    public String banner(Banner banner, Model model){
        List<Banner> banners = bannerService.getBanners(banner);
        model.addAttribute("title","微信banner管理");
        model.addAttribute("count",banners.size());
        model.addAttribute("banners",banners);
        return "wx/banner/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "wx/banner/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(@RequestPart("file") MultipartFile picture, Banner banner){
        System.out.println(picture.getOriginalFilename());
        System.out.println(picture.getName());
        int a = bannerService.save(banner);
        if (a != 0){
            return JsonUtils.success(banner,"添加广告成功");
        }else {
            return JsonUtils.error(banner);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile picture){
        return bannerService.upload(picture);
    }
}
