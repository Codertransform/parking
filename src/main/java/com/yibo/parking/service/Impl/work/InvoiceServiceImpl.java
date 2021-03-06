package com.yibo.parking.service.Impl.work;

import com.yibo.parking.dao.work.ContractorMapper;
import com.yibo.parking.dao.work.InvoiceMapper;
import com.yibo.parking.dao.work.NormalTollMapper;
import com.yibo.parking.entity.work.Contractor;
import com.yibo.parking.entity.work.Invoice;
import com.yibo.parking.entity.work.NormalToll;
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

    @Autowired
    private ContractorMapper contractorMapper;

    @Autowired
    private NormalTollMapper normalTollMapper;

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
        }else {
            invoice.setId(EntityIdGenerate.generateId());
            invoice.setStatus("0");
            invoice.setInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            int i = invoiceMapper.insert(invoice);
            map.put("flag", i);
            map.put("message", "添加发票成功");
        }
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
            System.out.println(invoice.getNumStart().charAt(0));
            Invoice in = new Invoice();
            in.setId(EntityIdGenerate.generateId());
            if (invoice.getNumStart().charAt(0) == '0'){
                in.setNumStart("0"+String.valueOf(s));
                System.out.println(in.getNumStart());
            }else {
                in.setNumStart(String.valueOf(s));
            }
            s = s + step - 1;
            if (invoice.getNumEnd().charAt(0) == '0'){
                in.setNumEnd("0"+String.valueOf(s));
                System.out.println(in.getNumEnd());
            }else {
                in.setNumEnd(String.valueOf(s));
            }
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
        List<Invoice> invoices = invoiceMapper.findByStatus(invoice);
        for (Invoice in : invoices){
            Contractor contractor = new Contractor();
            contractor.setId(in.getOuter());
            Contractor con = contractorMapper.get(contractor);
            if (con != null) {
                in.setOuter(con.getName());
            }
            NormalToll normalToll = new NormalToll();
            normalToll.setId(in.getOuter());
            NormalToll nor = normalTollMapper.get(normalToll);
            if (nor != null) {
                in.setOuter(nor.getName());
            }
        }
        return invoices;
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

    public Map<String, Object> getInvoice(Invoice invoice) {
        Map<String, Object> map = new HashMap<>();
        Invoice inv = invoiceMapper.getByStatus(invoice);
        if (inv != null) {
            map.put("flag", 1);
            map.put("invoice", inv);
            map.put("message", "该发票待核销");
        }else {
            map.put("flag", 0);
            map.put("message", "未检测到该发票");
        }
        return map;
    }

    public Map<String, Object> checkSave(Invoice invoice) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(invoice.getId());
        invoice.setWriteinTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        invoice.setStatus("2");
        int flag = invoiceMapper.updateWrite(invoice);
        if (flag != 0){
            map.put("flag", flag);
            map.put("message", "该发票已核销");
        }else {
            map.put("flag", flag);
            map.put("message", "系统错误，请联系管理员");
        }
        return map;
    }

    public int changeStatus(Invoice invoice) {
        invoice = invoiceMapper.get(invoice);
        invoice.setStatus("0");
        return invoiceMapper.updateStatus(invoice);
    }
}
