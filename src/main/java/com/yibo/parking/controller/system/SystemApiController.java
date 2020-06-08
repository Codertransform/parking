package com.yibo.parking.controller.system;

import com.yibo.parking.entity.system.SystemApi;
import com.yibo.parking.service.Impl.system.SystemApiServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/system/api")
public class SystemApiController {

    @Autowired
    private SystemApiServiceImpl apiService;

    @RequestMapping(value = {"","/"})
    public String index(SystemApi systemApi, Model model){
        List<SystemApi> apis = apiService.findList(systemApi);
        model.addAttribute("title","接口服务");
        model.addAttribute("apis",apis);
        if (apis.size() == 0){
            model.addAttribute("count", 0);
        }else {
            model.addAttribute("count", apis.size());
        }
        return "system/api/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title","添加接口");
        return "system/api/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(SystemApi api){
        return JsonUtils.success(api,"添加接口成功");
    }
}
