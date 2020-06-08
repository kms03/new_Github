package com.green.biz.board;

import java.util.List;

import com.green.biz.member.MemberVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

public interface BoardService {
	
	List<BoardVO> getBoardList(BoardVO vo);
	
	void insertBoard(BoardVO vo);
	
	BoardVO getBoard(int bseq);
	
	void updateCnt(int bseq);
	
	void updateBoard(BoardVO vo);
	
	void deleteBoard(BoardVO vo);
	
	List<BoardVO> boardList(SearchCriteria scri) throws Exception;
	
	int listCount(SearchCriteria scri) throws Exception;
	
	void deleteBoardById(String id);
	
	List<BoardVO> newBoard();
	
}
