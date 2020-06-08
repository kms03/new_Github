package com.green.biz.reply;

import java.util.List;

import com.green.biz.util.SearchCriteria;

public interface ReplyService {

	List<ReplyVO> boardReplyList(SearchCriteria scri) throws Exception;
	
	void insertReply(ReplyVO vo);
	
	void updateReply(ReplyVO vo);
	
	void deleteReply(ReplyVO vo);
	
	ReplyVO getReply(int rno);
	
	void deleteReplyById(String id);
	
	int replyCount(SearchCriteria scri) throws Exception;
	
	List<ReplyVO> adminReply(int bseq);
}
