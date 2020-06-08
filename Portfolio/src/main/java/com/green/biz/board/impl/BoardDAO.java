package com.green.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.board.BoardVO;
import com.green.biz.member.MemberVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> mybatis로 getBoardList() 기능 실행");
		
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> mybatis로 getBoardList() 기능 실행");
		
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	public BoardVO getBoard(int bseq) {
		System.out.println("===> mybatis로 getBoardList() 기능 실행");
		
		return mybatis.selectOne("BoardDAO.getBoard", bseq);
	}
	
	public void updateCnt(int bseq) {
		
		mybatis.update("BoardDAO.updateCnt", bseq);
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("===> mybatis로 updateBoard() 기능 실행");
		
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> mybatis로 deleteBoard() 기능 실행");
		
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	
	public int countBoard() {
		
		return mybatis.selectOne("BoardDAO.countBoard");
		
	}
	
	public List<BoardVO> pagingBoard(PageMaker vo) {
		
		return mybatis.selectList("BoardDAO.pagingBoard", vo);
		
	}
	
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception {
		
		return mybatis.selectList("BoardDAO.listPage", scri);
	}
	
	public int listCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("BoardDAO.listCount", scri);
		
	}
	
	public void deleteBoardById(String id) {
		
		mybatis.delete("BoardDAO.deleteBoardById", id);
	}
	
	public List<BoardVO> newBoard() {
		
		return mybatis.selectList("BoardDAO.newBoard");
	}
	
}
