package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.InvoiceMapper;
import com.yibo.parking.entity.work.Invoice;
import com.yibo.parking.service.InvoiceService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public List<Invoice> findList(Invoice invoice) {
        return invoiceMapper.findList(invoice);
    }

    @Override
    public Invoice get(Invoice invoice) {
        return invoiceMapper.get(invoice);
    }

    @Override
    public Map<String, Object> save(Invoice invoice) {
        Map<String, Object> map = new HashMap<>();
        if (invoice.getId() != null) {
            int u = invoiceMapper.update(invoice);
            map.put("flag", u);
            map.put("message", "修改发票成功");
        }
        invoice.setId(EntityIdGenerate.generateId());
        invoice.setStatus("0");
        invoice.setInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("flag",invoiceMapper.insert(invoice));
        map.put("message", "添加发票成功");
        return map;
    }

    @Override
    public int delete(Invoice invoice) {
        return invoiceMapper.delete(invoice);
    }

    public Map<String, Object> saves(Invoice invoice) {
        Map<String, Object> map = new HashMap<>();
        int flag = 0;
        int step = 0;
        int s = Integer.parseInt(invoice.getNumStart());
        if (invoice.getStep() != null){
            step = invoice.getStep();
        }else {
            step = 25;
        }
        while (s < Integer.parseInt(invoice.getNumEnd())){
            Invoice in = new Invoice();
            in.setId(EntityIdGenerate.generateId());
            in.setNumStart(String.valueOf(s));
            s = s + step - 1;
            in.setNumEnd(String.valueOf(s));
            in.setCode(invoice.getCode());
            in.setDenomination(invoice.getDenomination());
            in.setInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            in.setStatus("0");
            invoiceMapper.insert(in);
            s++;
            flag++;
        }
        map.put("flag", flag);
        map.put("message", "批量添加发票成功");
        return map;
    }

    public List<Invoice> findByStatus(Invoice invoice) {
        return invoiceMapper.findByStatus(invoice);
    }

    public Map<String, Object> reSave(Invoice invoice) {
        System.out.println(invoice.getRemarks());
        Map<String, Object> map = new HashMap<>();
        Invoice in = invoiceMapper.get(invoice);
        if (in == null){
            map.put("flag", 0);
            map.put("message", "信息有误，请重新输入！");
        }else {
            in.setOuter(invoice.getOuter());
            in.setOutTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            in.setStatus("1");
            in.setRemarks(invoice.getRemarks());
            map.put("flag", invoiceMapper.updateOut(in));
            map.put("message", "领取成功！");
        }
        return map;
    }
}
