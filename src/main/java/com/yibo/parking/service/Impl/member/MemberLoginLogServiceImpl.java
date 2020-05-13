package com.yibo.parking.service.Impl.member;

import com.yibo.parking.dao.member.MemberLoginLogDao;
import com.yibo.parking.entity.member.MemberLoginLog;
import com.yibo.parking.service.MemberLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginLogServiceImpl implements MemberLoginLogService {

    @Autowired
    private MemberLoginLogDao logDao;

    @Override
    public MemberLoginLog get(MemberLoginLog log) {
        return logDao.get(log);
    }

    @Override
    public int insert(MemberLoginLog log) {
        return logDao.insert(log);
    }

    @Override
    public int update(MemberLoginLog log) {
        return logDao.update(log);
    }

    public MemberLoginLog findByOpenId(String openid) {
        MemberLoginLog log = new MemberLoginLog();
        log.setOpenId(openid);
        return logDao.get(log);
    }
}
