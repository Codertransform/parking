package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.TypeServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class carController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private TypeServiceImpl typeService;

    @RequestMapping(value = {"","/"})
    public String index(String typeId, String logmin, String logmax, String cardId, Model model){
        List<Car> list = carService.getCars(typeId,logmin,logmax,cardId);
        List<Type> types = typeService.getTypes();
        model.addAttribute("title","车辆管理");
        model.addAttribute("list",list);
        model.addAttribute("types",types);
        model.addAttribute("count", list.size());
        model.addAttribute("typeId",typeId);
        model.addAttribute("logmin", logmin);
        model.addAttribute("logmax", logmax);
        model.addAttribute("cardId", cardId);
        return "cars/index";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Type> types = typeService.getTypes();
        model.addAttribute("types",types);
        return "cars/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Car car){
        int i = carService.save(car);
        if (i == 1){
            return JsonUtils.success(car,"保存车辆信息成功");
        }else {
            return JsonUtils.error(car);
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(String id, Model model){
        Car car = carService.get(id);
        List<Type> types = typeService.getTypes();
        model.addAttribute("types",types);
        model.addAttribute("car", car);
        return "cars/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(String id){
        int d = carService.del(id);
        if (d == 1){
            return JsonUtils.success(id, "删除车辆信息成功");
        }else {
            return JsonUtils.error(id);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dels")
    public String dels(@RequestParam("ids[]") String[] ids){
        int d = carService.dels(ids);
        if (d > 0)
            return JsonUtils.success(ids, "批量删除成功");
        else
            return JsonUtils.error(ids);
    }

    @ResponseBody
    @RequestMapping(value = "/stop")
    public String stop(String id){
        int s = carService.stop(id);
        if (s > 0) return JsonUtils.success(id, "车辆已停用");
        else return JsonUtils.error(id);
    }

    @ResponseBody
    @RequestMapping(value = "/start")
    public String start(String id){
        int s = carService.start(id);
        if (s > 0) return JsonUtils.success(id, "车辆已启用");
        else return JsonUtils.error(id);
    }
}
