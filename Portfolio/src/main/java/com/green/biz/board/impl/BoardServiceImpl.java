package com.green.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.board.BoardService;
import com.green.biz.board.BoardVO;
import com.green.biz.member.MemberVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		return boardDAO.getBoardList(vo);
	}

	@Override
	public void insertBoard(BoardVO vo) {
		
		boardDAO.insertBoard(vo);
		
	}

	@Override
	public BoardVO getBoard(int bseq) {
		
		return boardDAO.getBoard(bseq);
	}

	@Override
	public void updateCnt(int bseq) {
		
		boardDAO.updateCnt(bseq);
		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		
		boardDAO.updateBoard(vo);
		
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		
		boardDAO.deleteBoard(vo);
		
	}

	@Override
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception {
		
		return boardDAO.boardList(scri);
	}

	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		
		return boardDAO.listCount(scri);
	}

	@Override
	public void deleteBoardById(String id) {
		
		boardDAO.deleteBoardById(id);
		
	}

	@Override
	public List<BoardVO> newBoard() {
		
		return boardDAO.newBoard();
	}

}
