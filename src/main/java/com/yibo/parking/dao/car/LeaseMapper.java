package com.yibo.parking.dao.car;

import com.yibo.parking.entity.car.Lease;
import com.yibo.parking.entity.member.MemberWxInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaseMapper {

    public Lease get(Lease lease);

    public List<Lease> getLeases(Lease lease);

    public int insert(Lease lease);

    public int update(Lease lease);

    public int delete(String id);

    int check(Lease lease);

    Lease getByCarId(@Param("carId") String carId);

    List<Lease> findByMemberId(@Param("memberId") String memberId);

    List<Lease> findByCarId(@Param("carId") String carId);

    List<Lease> findByMemberType(MemberWxInfo info);
}
