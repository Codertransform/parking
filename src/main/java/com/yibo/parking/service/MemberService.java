package com.yibo.parking.service;

import com.yibo.parking.entity.member.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    List<Member> getMembers(Member member);

    Member get(Member member);

    Map<String,Object> save(Member member);

    Map<String,Object> delete(Member member);
}
