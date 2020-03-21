package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Type;
import com.yibo.parking.entity.util.Json;
import com.yibo.parking.service.Impl.car.TypeServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/type")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        List<Type> types = typeService.getTypes();
        model.addAttribute("types",types);
        model.addAttribute("count",types.size());
        model.addAttribute("title","车辆类型");
        return "type/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "type/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Type type){
        int i = typeService.save(type);
        if (i != 0)
        return JsonUtils.success(type,"车辆类型添加成功");
        else return JsonUtils.error(type);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(String id){
        int d = typeService.del(id);
        if (d != 0)
        return JsonUtils.success(id,"车辆类型删除成功");
        else return JsonUtils.error(id);
    }
}
