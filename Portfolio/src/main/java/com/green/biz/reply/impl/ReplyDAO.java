package com.green.biz.reply.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.reply.ReplyVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.SearchCriteria;

@Repository
public class ReplyDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ReplyVO> boardReplyList(SearchCriteria scri) throws Exception {
		
		return mybatis.selectList("ReplyDAO.readReply", scri);
		
	}
	
	public void insertReply(ReplyVO vo) {
		System.out.println("===> mybatis�� insertReply() ��� ����");
		
		mybatis.insert("ReplyDAO.insertReply", vo);
	}
	
	public void updateReply(ReplyVO vo) {
		System.out.println("===> mybatis�� updateReply() ��� ����");
		
		mybatis.update("ReplyDAO.updateReply", vo);
	}
	
	public void deleteReply(ReplyVO vo) {
		System.out.println("===> mybatis�� deleteReply() ��� ����");
		
		mybatis.delete("ReplyDAO.deleteReply", vo);
	}
	
	public ReplyVO getReply(int rno) {
		System.out.println("===> mybatis�� deleteReply() ��� ����");
		
		return mybatis.selectOne("ReplyDAO.getReply", rno);
	}
	
	public void deleteReplyById(String id) {
		
		mybatis.delete("ReplyDAO.deleteReplyById", id);
	}
	
	public int replyCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("ReplyDAO.replyCount", scri);
	}
	
	public List<ReplyVO> adminReply(int bseq) {
		
		return mybatis.selectList("ReplyDAO.boardReplyList");
	}
	
}
