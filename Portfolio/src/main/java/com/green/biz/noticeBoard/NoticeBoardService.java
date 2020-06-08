package com.green.biz.noticeBoard;

import java.util.List;

import com.green.biz.util.SearchCriteria;

public interface NoticeBoardService {
	
	List<NoticeBoardVO> noticeBoardList(SearchCriteria scri);
	
	NoticeBoardVO getNoticeBoard(NoticeBoardVO vo);
	
	void noticeBoardCnt(int nseq);
	
	int noticeBoardCount(SearchCriteria scri) throws Exception;
	
	List<NoticeBoardVO> newNoticeBoard();
	
	void insertNoticeBoard(NoticeBoardVO vo);
	
	void updateNoticeBoard(NoticeBoardVO vo);
	
	void deleteNoticeBoard(int nseq);
}
