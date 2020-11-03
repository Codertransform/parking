package com.yibo.parking.dao.work;

import com.yibo.parking.entity.work.Contractor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractorMapper {

    List<Contractor> findList(Contractor contractor);

    Contractor get(Contractor contractor);

    int insert(Contractor contractor);

    int update(Contractor contractor);

    int delete(Contractor contractor);
}
