package com.yibo.parking.service;

import com.yibo.parking.entity.work.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    public List<Invoice> findList(Invoice invoice);

    public Invoice get(Invoice invoice);

    public Map<String,Object> save(Invoice invoice);

    public int delete(Invoice invoice);
}
