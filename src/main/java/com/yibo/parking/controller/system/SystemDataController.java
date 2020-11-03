package com.yibo.parking.controller.system;

import com.yibo.parking.entity.system.SystemData;
import com.yibo.parking.service.Impl.system.SystemDataServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/system/data")
public class SystemDataController {

    @Autowired
    private SystemDataServiceImpl dataService;

    @RequestMapping(value = {"","/"})
    public String index(SystemData systemData, Model model){
        List<SystemData> datas = dataService.findList(systemData);
        model.addAttribute("title","数据字典");
        model.addAttribute("datas",datas);
        return "/system/data/index";
    }

    @RequestMapping(value = "/edit")
    public String edit(SystemData systemData, Model model){
        systemData = dataService.get(systemData);
        model.addAttribute("title", "字典信息修改");
        model.addAttribute("systemData", systemData);
        return "system/data/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SystemData systemData){
        int s = dataService.save(systemData);
        if (s != 0) {
            return JsonUtils.success(systemData,"添加字典成功！");
        }
        return JsonUtils.error(systemData);
    }
}
