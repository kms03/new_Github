package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.util.SearchCriteria;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public MemberVO getMember(String id) {
		
		return memberDAO.getMember(id);
	}

	@Override
	public int loginMember(String id, String pwd) {
		int result = -1;
		
		String member = memberDAO.loginMember(id);
		
		if(member != null) { // DB에 id가 있는 경우
			if(pwd.equals(member)) { // DB에 id가 있고 pwd가 일치하는 경우 로그인 성공
				result = 1;
			} else { // DB에 id가 있고 pwd가 불일치하는 경우 로그인 실패
				result = 0;
			}
		} else { // DB에 id가 없는 경우 로그인 실패
			result = -1;
		}
		
		return result;
	}

	@Override
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception {
		
		return memberDAO.memberList(scri);
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		memberDAO.insertMember(vo);
		
	}

	@Override
	public MemberVO findId(MemberVO vo) {
		
		return memberDAO.findId(vo);
	}

	@Override
	public MemberVO findPwd(MemberVO vo) {
		
		return memberDAO.findPwd(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		
		memberDAO.updateMember(vo);
		
	}

	@Override
	public void updatePwd(MemberVO vo) {
		
		memberDAO.updatePwd(vo);
		
	}

	@Override
	public void deleteMember(String id) {
		
		memberDAO.deleteMember(id);
		
	}

	@Override
	public int memberCount(SearchCriteria scri) throws Exception {
		
		return memberDAO.memberCount(scri);
	}


}
