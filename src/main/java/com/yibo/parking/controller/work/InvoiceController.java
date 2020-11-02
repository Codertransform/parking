package com.yibo.parking.controller.work;

import com.yibo.parking.entity.work.Invoice;
import com.yibo.parking.service.Impl.work.InvoiceServiceImpl;
import com.yibo.parking.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/work/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @RequestMapping(value = {"","/"})
    public String index(Invoice invoice, Model model){
        List<Invoice> invoices = invoiceService.findList(invoice);
        model.addAttribute("title","发票列表");
        model.addAttribute("invoices",invoices);
        model.addAttribute("invoice",invoice);
        return "work/invoice/index";
    }

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("title","添加发票");
        return "work/invoice/add";
    }

    @RequestMapping(value = "/adds")
    public String adds(Model model){
        model.addAttribute("title","批量添加发票");
        return "work/invoice/adds";
    }

    @RequestMapping(value = "/edit")
    public String edit(Invoice invoice, Model model){
        model.addAttribute("title","修改发票");
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Invoice invoice){
        Map<String, Object> map = invoiceService.save(invoice);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(invoice, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(invoice);
    }

    @ResponseBody
    @RequestMapping(value = "/saves")
    public String saves(Invoice invoice){
        Map<String, Object> map = invoiceService.saves(invoice);
        int flag = (int) map.get("flag");
        if (flag != 0) {
            return JsonUtils.success(invoice, String.valueOf(map.get("message")));
        }
        return JsonUtils.error(invoice);
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(Invoice invoice){
        return "";
    }

    @RequestMapping(value = "/receive")
    public String receive(Model model){
        return "";
    }

    @RequestMapping(value = "/check")
    public String check(Invoice invoice){
        return "";
    }
}