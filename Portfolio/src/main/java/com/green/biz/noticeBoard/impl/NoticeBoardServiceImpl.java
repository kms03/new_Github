package com.green.biz.noticeBoard.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.noticeBoard.NoticeBoardService;
import com.green.biz.noticeBoard.NoticeBoardVO;
import com.green.biz.util.SearchCriteria;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	@Autowired
	private NoticeBoardDAO noticeBoardDAO;
	
	@Override
	public List<NoticeBoardVO> noticeBoardList(SearchCriteria scri) {
		
		return noticeBoardDAO.noticeBoardList(scri);
	}

	@Override
	public NoticeBoardVO getNoticeBoard(NoticeBoardVO vo) {
		
		return noticeBoardDAO.getNoticeBoard(vo);
	}

	@Override
	public void noticeBoardCnt(int nseq) {
		
		noticeBoardDAO.noticeBoardCnt(nseq);
		
	}

	@Override
	public int noticeBoardCount(SearchCriteria scri) throws Exception {
		
		return noticeBoardDAO.noticeBoardCount(scri);
	}

	@Override
	public List<NoticeBoardVO> newNoticeBoard() {
		
		return noticeBoardDAO.newNoticeBoard();
	}

	@Override
	public void insertNoticeBoard(NoticeBoardVO vo) {
		
		noticeBoardDAO.insertNoticeBoard(vo);
		
	}

	@Override
	public void updateNoticeBoard(NoticeBoardVO vo) {
		
		noticeBoardDAO.updateNoticeBoard(vo);
		
	}

	@Override
	public void deleteNoticeBoard(int nseq) {
		
		noticeBoardDAO.deleteNoticeBoard(nseq);
		
	}

}
