package com.yibo.parking.dao.work;

import com.yibo.parking.entity.work.IntelToll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntelTollMapper {

    List<IntelToll> findList(IntelToll intelToll);

    IntelToll get(IntelToll intelToll);

    int update(IntelToll intelToll);

    int insert(IntelToll intelToll);

    int delete(IntelToll intelToll);
}
