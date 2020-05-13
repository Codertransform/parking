package com.yibo.parking.service;

import com.yibo.parking.entity.member.MemberWxInfo;

public interface MemberWxInfoService {

    MemberWxInfo get(MemberWxInfo info);

    int insert(MemberWxInfo info);

    int update(MemberWxInfo info);
}
