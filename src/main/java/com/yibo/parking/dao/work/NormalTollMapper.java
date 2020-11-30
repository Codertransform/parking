package com.yibo.parking.dao.work;

import com.yibo.parking.entity.work.NormalToll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NormalTollMapper {

    List<NormalToll> findList(NormalToll normalToll);

    NormalToll get(NormalToll normalToll);

    int insert(NormalToll normalToll);

    int update(NormalToll normalToll);

    int delete(NormalToll normalToll);
}
