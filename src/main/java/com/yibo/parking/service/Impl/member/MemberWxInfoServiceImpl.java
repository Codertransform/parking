package com.yibo.parking.service.Impl.member;

import com.yibo.parking.dao.member.MemberLoginLogDao;
import com.yibo.parking.dao.member.MemberWxInfoDao;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.entity.member.MemberLoginLog;
import com.yibo.parking.entity.member.MemberWxInfo;
import com.yibo.parking.service.MemberWxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberWxInfoServiceImpl implements MemberWxInfoService {

    @Autowired
    private MemberWxInfoDao infoDao;

    @Autowired
    private MemberLoginLogDao logDao;

    @Override
    public MemberWxInfo get(MemberWxInfo info) {
        return infoDao.get(info);
    }

    public MemberWxInfo getByOpenId(String openid) {
        MemberWxInfo info = new MemberWxInfo();
        info.setOpenId(openid);
        return infoDao.get(info);
    }

    @Override
    public int insert(MemberWxInfo info) {
        return infoDao.insert(info);
    }

    @Override
    public int update(MemberWxInfo info){
        return infoDao.update(info);
    }

    public MemberWxInfo findBySkey(String skey) {
        MemberWxInfo info = new MemberWxInfo();
        info.setSkey(skey);
        return infoDao.get(info);
    }

    public int certify(MemberWxInfo info, String id) {
        Member m = new Member();
        m.setId(id);
        info.setMember(m);
        info.setStatus(1);
        int ui = infoDao.update(info);
        MemberLoginLog log = logDao.findByOpenId(info.getOpenId());
        log.setMember(m);
        int ul = logDao.update(log);
        if (ui != 0 && ul != 0){
            return 1;
        }
        return 0;
    }
}
