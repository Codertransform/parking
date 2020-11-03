package com.yibo.parking.dao.work;

import com.yibo.parking.entity.work.Invoice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    List<Invoice> findList(Invoice invoice);

    Invoice get(Invoice invoice);

    int insert(Invoice invoice);

    int update(Invoice invoice);

    int delete(Invoice invoice);

    int updateStatus(Invoice invoice);

    List<Invoice> findByStatus(Invoice invoice);
}
