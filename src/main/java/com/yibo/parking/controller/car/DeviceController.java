package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Device;
import com.yibo.parking.entity.system.SystemServer;
import com.yibo.parking.entity.unit.Unit;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.DeviceServiceImpl;
import com.yibo.parking.service.Impl.system.SystemServerServiceImpl;
import com.yibo.parking.service.Impl.unit.UnitServiceIpml;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cars/device")
public class DeviceController {

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private SystemServerServiceImpl serverService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private UnitServiceIpml unitServiceIpml;

    @RequestMapping(value = {"","/"})
    public String index(Device device, Model model){
        List<Device> devices = deviceService.findList(device);
        model.addAttribute("title","设备管理");
        model.addAttribute("list",devices);
        model.addAttribute("count",devices.size());
        if (device != null && device.getDeviceId() != null && device.getCarId() != null){
            model.addAttribute("deviceId",device.getDeviceId());
            model.addAttribute("carId",device.getCarId());
        }
        return "cars/device/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        List<SystemServer> servers = serverService.findList(new SystemServer());
        List<Car> cars = carService.getAllCars();
        List<Unit> units = unitServiceIpml.getUnits();
        model.addAttribute("title","设备添加");
        model.addAttribute("servers",servers);
        model.addAttribute("cars",cars);
        model.addAttribute("units",units);
        return "cars/device/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Device device, Model model){
        List<SystemServer> servers = serverService.findList(new SystemServer());
        List<Car> cars = carService.getAllCars();
        List<Unit> units = unitServiceIpml.getUnits();
        model.addAttribute("title","修改设备信息");
        model.addAttribute("servers",servers);
        model.addAttribute("cars",cars);
        model.addAttribute("units",units);
        model.addAttribute("dev",deviceService.get(device));
        return "cars/device/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Device device){
        int s = deviceService.save(device);
        if (s != 0) {
            return JsonUtils.success(device,"设备保存成功");
        }
        return JsonUtils.error(device);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Device device){
        int d = deviceService.delete(device);
        if (d != 0) {
            return JsonUtils.success(device,"设备删除成功");
        }
        return JsonUtils.error(device);
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public String status(Device device){
        Map<String,Object> map = deviceService.status(device);
        boolean flag = (boolean) map.get("flag");
        if (flag){
            return JsonUtils.success(map.get("device"),"设备已停用");
        }
        return JsonUtils.error(null);
    }
}
