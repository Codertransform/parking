package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.service.Impl.car.CarServiceImpl;
import com.yibo.parking.service.Impl.car.LeaseServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cars/lease")
public class leaseController {

    @Autowired
    private LeaseServiceImpl leaseService;

    @Autowired
    private CarServiceImpl carService;

    @RequestMapping(value = {"","/"})
    public String index(Lease lease, Model model){
        List<Lease> leases = leaseService.getLeases(lease);
        List<Type> types = leaseService.getTypes();
        model.addAttribute("leases",leases);
        model.addAttribute("types",types);
        model.addAttribute("lease",lease);
        model.addAttribute("title","租用订单");
        return "lease/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String add(Lease lease, Model model){
        lease = leaseService.get(lease);
        model.addAttribute("lease", lease);
        return "lease/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String toAdd(Lease lease){
        int a = leaseService.save(lease);
        if (a != 0)
            return JsonUtils.success(lease,"租用订单添加成功");
        else return JsonUtils.error(lease);
    }

    @ResponseBody
    @RequestMapping(value = "/check")
    public String check(Lease lease){
        Map<String,Object> map = leaseService.check(lease);
        int c = (int) map.get("flag");
        if (c != 0) {
            return JsonUtils.success(map.get("lease"), String.valueOf(map.get("msg")));
        }
        return JsonUtils.error(map.get("lease"));
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Lease lease){
        Map<String,Object> map = leaseService.delete(lease.getId());
        boolean flag = (boolean) map.get("flag");
        if (flag) {
            return JsonUtils.success(lease, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(lease);
    }

    @ResponseBody
    @RequestMapping(value = "/dels")
    public String dels(@RequestParam("ids[]") String[] ids){
        int d = leaseService.dels(ids);
        if (d > 0) {
            return JsonUtils.success(ids,"批量删除成功");
        }
        return JsonUtils.error(ids);
    }
}
