package com.green.biz.noticeBoard.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.noticeBoard.NoticeBoardVO;
import com.green.biz.util.SearchCriteria;

@Repository
public class NoticeBoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<NoticeBoardVO> noticeBoardList(SearchCriteria scri) {
		
		return mybatis.selectList("NoticeBoardDAO.noticeBoardList", scri);
	}
	
	public NoticeBoardVO getNoticeBoard(NoticeBoardVO vo) {
		
		return mybatis.selectOne("NoticeBoardDAO.getNoticeBoard", vo);
	}
	
	public void noticeBoardCnt(int nseq) {
		
		mybatis.update("NoticeBoardDAO.noticeBoardCnt", nseq);
	}
	
	public int noticeBoardCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("NoticeBoardDAO.noticeBoardCount", scri);
	}
	
	public List<NoticeBoardVO> newNoticeBoard() {
		
		return mybatis.selectList("NoticeBoardDAO.newNoticeBoard");
	}
	
	public void insertNoticeBoard(NoticeBoardVO vo) {
		
		mybatis.insert("NoticeBoardDAO.insertNoticeBoard", vo);
	}
	
	public void updateNoticeBoard(NoticeBoardVO vo) {
		
		mybatis.update("NoticeBoardDAO.updateNoticeBoard", vo);
	}
	
	public void deleteNoticeBoard(int nseq) {
		
		mybatis.delete("NoticeBoardDAO.deleteNoticeBoard", nseq);
	}
	
}
