package com.green.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.member.MemberVO;
import com.green.biz.util.SearchCriteria;

@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate mybatis;
	
	public MemberVO getMember(String id) {
		System.out.println("===>mybatis로 getMember() 기능 수행");
		
		return  mybatis.selectOne("MemberDAO.getMember", id);
	}
	
	public String loginMember(String id) {
		System.out.println("===>mybatis로 getMember() 기능 수행");
		
		return mybatis.selectOne("MemberDAO.loginMember", id);
	}
	
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception {
		return mybatis.selectList("MemberDAO.memberList", scri);
	}
	
	public void insertMember(MemberVO vo) {
		System.out.println("===>mybatis로 insertMember() 기능 수행");
		
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public MemberVO findId(MemberVO vo) {
		System.out.println("===>mybatis로 insertMember() 기능 수행");
		
		return mybatis.selectOne("MemberDAO.findId", vo);
	}
	
	public MemberVO findPwd(MemberVO vo) {
		System.out.println("===>mybatis로 findPwd() 기능 수행");
		
		return mybatis.selectOne("MemberDAO.findPwd", vo);
	}
	
	public void updateMember(MemberVO vo) {
		
		mybatis.update("MemberDAO.updateMember", vo);
		
	}
	
	public void updatePwd(MemberVO vo) {
		
		mybatis.update("MemberDAO.updatePwd", vo);
		
	}
	
	public void deleteMember(String id) {
		
		mybatis.delete("MemberDAO.deleteMember", id);
	}
	
	public int memberCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("MemberDAO.memberCount", scri);
	}
	
}
