package com.yibo.parking.controller.wx;

import com.yibo.parking.entity.wx.Menu;
import com.yibo.parking.service.Impl.wx.WxMenusServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/menu")
public class WxMenuController {

    @Autowired
    private WxMenusServiceImpl menusService;

    @RequestMapping(value = {"","/"})
    public String index(Menu menu, Model model){
        List<Menu> menus = menusService.getMenus(new Menu());
        model.addAttribute("menu",menu);
        model.addAttribute("menus",menus);
        model.addAttribute("count",menus.size());
        return "wx/menu/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "wx/menu/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Menu menu){
        int i = menusService.save(menu);
        if (i != 0) {
            return JsonUtils.success(menu,"添加成功");
        }else {
            return JsonUtils.error(menu);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadIcon(@RequestParam("file") MultipartFile icon){
        Map<String,String> path = menusService.upload(icon);
        if (path.containsKey("src")){
            return JsonUtils.success(path,"上传成功");
        }else {
            return JsonUtils.error(path);
        }
    }
}
