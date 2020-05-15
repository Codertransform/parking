package com.yibo.parking.service.Impl.member;

import com.yibo.parking.dao.member.MemberDao;
import com.yibo.parking.entity.member.Member;
import com.yibo.parking.service.MemberService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Member> getMembers(Member member) {
        return memberDao.getMembers(member);
    }

    @Override
    public Member get(Member member) {
        return memberDao.get(member);
    }

    @Override
    public Map<String,Object> save(Member member) {
        Map<String,Object> map = new HashMap<>();
        if (member.getId() != null){
            int u = memberDao.update(member);
            if (u != 0){
                map.put("code",0);
                map.put("message","修改成功");
            }
            return map;
        }else {
            member.setId(EntityIdGenerate.generateId());
            member.setStatus("0");
            int i = memberDao.insert(member);
            if (i != 0) {
                map.put("code",0);
                map.put("message","添加成功");
            }
            return map;
        }
    }

    @Override
    public Map<String,Object> delete(Member member) {
        Map<String,Object> map = new HashMap<>();
        int d = memberDao.delete(member);
        if (d == 0){
            map.put("code",-9);
            map.put("message","删除失败");
        }else {
            map.put("code",0);
            map.put("message","删除成功");
        }
        return map;
    }

    public Map<String,Object> status(Member member){
        Map<String,Object> map = new HashMap<>();
        int s = memberDao.status(member);
        if (s == 0){
            map.put("code", -9);
            map.put("message","信息有误，操作失败！");
            return map;
        }
        switch (member.getStatus()){
            case "1":
                map.put("code",1);
                map.put("message","已通过");
                break;
            case "-1":
                map.put("code",-1);
                map.put("message","未通过");
                break;
            case "-2":
                map.put("code",-2);
                map.put("message","已下架");
                break;
            default:
                map.put("code",2);
                map.put("message", "已上架");
                break;
        }
        return map;
    }

    public Member findByOpenId(String openid) {
        Member member = new Member();
        member.setOpenId(openid);
        return memberDao.get(member);
    }

    public Member findMember(String name, String tel, String idCrad) {
        Member member = new Member();
        member.setUsername(name);
        member.setTel(tel);
        member.setIdCard(idCrad);
        return memberDao.get(member);
    }

    public int certify(Member member, String openId) {
        member.setOpenId(openId);
        return memberDao.update(member);
    }
}
