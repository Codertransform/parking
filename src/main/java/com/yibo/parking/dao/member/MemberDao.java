package com.yibo.parking.dao.member;

import com.yibo.parking.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    List<Member> getMembers(Member member);

    Member get(Member member);

    int insert(Member member);

    int update(Member member);

    int delete(Member member);

    int status(Member member);
}
