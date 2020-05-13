package com.yibo.parking.service;

import com.yibo.parking.entity.member.MemberLoginLog;

public interface MemberLoginLogService {

    MemberLoginLog get(MemberLoginLog log);

    int insert(MemberLoginLog log);

    int update(MemberLoginLog log);
}
