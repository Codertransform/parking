package com.yibo.parking.controller.wx;

import com.yibo.parking.entity.wx.Banner;
import com.yibo.parking.service.Impl.wx.WxBannerServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
        model.addAttribute("banner",banner);
        return "wx/banner/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "wx/banner/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Banner banner){
        int a = bannerService.save(banner);
        if (a != 0){
            return JsonUtils.success(banner,"添加广告成功");
        }else {
            return JsonUtils.error(banner);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestPart("file") MultipartFile picture) {
        Map<String,String> path = bannerService.upload(picture);
        if (path.containsKey("src")){
            return JsonUtils.success(path,"上传成功");
        }else {
            return JsonUtils.error(path);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Banner banner, Model model){
        model.addAttribute("banner",bannerService.get(banner));
        return "wx/banner/edit";
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String toEdit(Banner banner){
        int u = bannerService.save(banner);
        if (u != 0){
            return JsonUtils.success(banner,"更新banner成功");
        }else {
            return JsonUtils.error(banner);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Banner banner){
        int d = bannerService.delete(banner);
        if (d != 0){
            return JsonUtils.success(banner,"删除本条banner成功");
        }
        return JsonUtils.error(banner);
    }

    @ResponseBody
    @RequestMapping(value = "/dels")
    public String dels(@RequestParam("ids[]") String[] ids){
        int d = bannerService.dels(ids);
        if (d != 0){
            return JsonUtils.success(ids,"删除成功");
        }
        return JsonUtils.error(ids);
    }

    @ResponseBody
    @RequestMapping(value = "/up")
    public String upStatus(Banner banner){
        int s = bannerService.changStatus(banner);
        if (s != 0){
            return JsonUtils.success(banner,"已上架");
        }
        return JsonUtils.error(banner);
    }

    @ResponseBody
    @RequestMapping(value = "/down")
    public String downStatus(Banner banner){
        int s = bannerService.changStatus(banner);
        if (s != 0){
            return JsonUtils.success(banner,"已下架");
        }
        return JsonUtils.error(banner);
    }
}
