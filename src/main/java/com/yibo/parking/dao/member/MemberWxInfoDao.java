package com.yibo.parking.dao.member;

import com.yibo.parking.entity.member.MemberWxInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberWxInfoDao {

    MemberWxInfo get(MemberWxInfo info);

    MemberWxInfo getByOpenId(String openid);

    int insert(MemberWxInfo info);

    int update(MemberWxInfo info);
}
