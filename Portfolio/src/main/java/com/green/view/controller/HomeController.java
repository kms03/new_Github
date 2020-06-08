package com.green.view.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.board.BoardService;
import com.green.biz.board.BoardVO;
import com.green.biz.noticeBoard.NoticeBoardService;
import com.green.biz.noticeBoard.NoticeBoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private NoticeBoardService noticeBoardService;
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 메인 화면 호출
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String home(Model model) {
		// 공지사항 리스트 중 최근 항목 4개를 가져옴 
		List<NoticeBoardVO> noticeBoard = noticeBoardService.newNoticeBoard();
		// 게시판 리스트 중 최근 항목 4개를 가져옴 
		List<BoardVO> boardList = boardService.newBoard();
		
		model.addAttribute("noticeBoard", noticeBoard);
		model.addAttribute("boardList", boardList);
		
		return "main";
	}
	
}
