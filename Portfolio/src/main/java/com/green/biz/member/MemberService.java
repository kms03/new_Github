package com.green.biz.member;

import java.util.List;

import com.green.biz.util.SearchCriteria;

public interface MemberService {
	
	MemberVO getMember(String id);
	
	int loginMember(String id, String pwd);
	
	List<MemberVO> memberList(SearchCriteria scri) throws Exception;
	
	void insertMember(MemberVO vo);
	
	MemberVO findId(MemberVO vo);
	
	MemberVO findPwd(MemberVO vo);
	
	void updateMember(MemberVO vo);
	
	void updatePwd(MemberVO vo);
	
	void deleteMember(String id);
	
	int memberCount(SearchCriteria scri) throws Exception;
}
