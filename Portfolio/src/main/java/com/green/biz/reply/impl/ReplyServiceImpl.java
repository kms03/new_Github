package com.green.biz.reply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.reply.ReplyService;
import com.green.biz.reply.ReplyVO;
import com.green.biz.util.SearchCriteria;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public List<ReplyVO> boardReplyList(SearchCriteria scri) throws Exception {
		
		return replyDAO.boardReplyList(scri);
	}

	@Override
	public void insertReply(ReplyVO vo) {
		
		replyDAO.insertReply(vo);
		
	}

	@Override
	public void updateReply(ReplyVO vo) {
		
		replyDAO.updateReply(vo);
		
	}

	@Override
	public void deleteReply(ReplyVO vo) {
		
		replyDAO.deleteReply(vo);
		
	}

	@Override
	public ReplyVO getReply(int rno) {
		
		return replyDAO.getReply(rno);
	}

	@Override
	public void deleteReplyById(String id) {
		
		replyDAO.deleteReplyById(id);
		
	}

	@Override
	public int replyCount(SearchCriteria scri) throws Exception {
		
		return replyDAO.replyCount(scri);
	}

	@Override
	public List<ReplyVO> adminReply(int bseq) {
		
		return replyDAO.adminReply(bseq);
	}

}
