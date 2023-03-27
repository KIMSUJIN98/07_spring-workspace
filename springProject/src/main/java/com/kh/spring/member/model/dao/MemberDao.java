package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDao {
	
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {			// SqlSessionTemplate에 있는 것을 가져와야 함.
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}

}
