package com.yibo.parking.dao.member;

import com.yibo.parking.entity.member.MemberLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberLoginLogDao {

    MemberLoginLog get(MemberLoginLog log);

    int insert(MemberLoginLog log);

    int update(MemberLoginLog log);

    MemberLoginLog findByOpenId(@Param("openId") String openId);
}
