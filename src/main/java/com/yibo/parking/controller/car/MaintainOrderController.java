package com.yibo.parking.controller.car;

import com.yibo.parking.entity.car.MaintainOrder;
import com.yibo.parking.service.Impl.car.MaintainOrderServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/maintain")
public class MaintainOrderController {

    @Autowired
    private MaintainOrderServiceImpl orderService;

    @RequestMapping(value = {"","/"})
    public String index(Model model){
        List<MaintainOrder> orders = orderService.getOrders();
        model.addAttribute("title","车辆保养");
        model.addAttribute("count",orders.size());
        model.addAttribute("orders",orders);
        return "maintain/index";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "maintain/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String toAdd(MaintainOrder order){
        int i = orderService.save(order);
        if (i == '1') return JsonUtils.success(order,"订单添加成功");
        else return JsonUtils.error(order);
    }

    @ResponseBody
    @RequestMapping(value = "/check")
    public String check(String id, String status){
        int u = orderService.check(id,status);
        if (u == 1){
            return JsonUtils.success(id,"审核成功");
        }else {
            return JsonUtils.error(id);
        }
    }
}
