package com.yibo.parking.controller.system;

import com.yibo.parking.entity.system.SystemServer;
import com.yibo.parking.service.Impl.system.SystemServerServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/system/server")
public class SystemServerController {

    @Autowired
    private SystemServerServiceImpl serverService;

    @RequestMapping(value = {"","/"})
    public String index(SystemServer systemServer, Model model){
        List<SystemServer> servers = serverService.findList(systemServer);
        model.addAttribute("title","接口服务");
        model.addAttribute("servers",servers);
        if (servers.size() == 0){
            model.addAttribute("count", 0);
        }else {
            model.addAttribute("count", servers.size());
        }
        return "system/server/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title","添加服务");
        return "system/server/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(SystemServer server){
        return JsonUtils.success(server,"添加接口成功");
    }
}
