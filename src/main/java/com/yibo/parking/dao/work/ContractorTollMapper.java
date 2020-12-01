package com.yibo.parking.dao.work;

import com.yibo.parking.entity.work.ContractorToll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractorTollMapper {

    List<ContractorToll> findList(ContractorToll contractorToll);

    ContractorToll get(ContractorToll contractorToll);

    int insert(ContractorToll contractorToll);

    int update(ContractorToll contractorToll);

    int delete(ContractorToll contractorToll);

}
