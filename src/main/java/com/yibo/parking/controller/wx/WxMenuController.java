package com.yibo.parking.controller.wx;

import com.yibo.parking.entity.wx.Menu;
import com.yibo.parking.service.Impl.wx.WxMenusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
