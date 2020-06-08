package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.biz.member.WorkerVO;
import com.green.biz.noticeBoard.NoticeBoardService;
import com.green.biz.noticeBoard.NoticeBoardVO;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@Controller
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@RequestMapping("/noticeBoard") // 공지사항 리스트를 화면에 출력
	public String noticeBoardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		// 리스트 페이징 설정 처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(noticeBoardService.noticeBoardCount(scri));
		
		List<NoticeBoardVO> noticeBoardList = noticeBoardService.noticeBoardList(scri);
		
		model.addAttribute("noticeBoardList", noticeBoardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "noticeBoard/noticeBoardList";
	}
	
	@RequestMapping("/noticeBoard_detail") // 공지사항 상세화면 출력
	public String noticeBoardDetail(Model model, NoticeBoardVO vo) {
		// 상세 화면 조회시 조회수가 +1씩 증가
		noticeBoardService.noticeBoardCnt(vo.getNseq());
		NoticeBoardVO noticeBoard =  noticeBoardService.getNoticeBoard(vo);
		
		model.addAttribute("noticeBoard", noticeBoard);
		
		return "noticeBoard/noticeBoardDetail";
	}
	
	@RequestMapping("/noticeBoard_write_form") // 공지사항 글쓰기 화면 출력
	public String noticeBoardWriteForm(HttpSession session) {
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) { // 관리자 로그인이 안되어 있었을시
			return "redirect:main";
		}
		
		return "noticeBoard/noticeBoardWriteForm";
	}
	
	@RequestMapping("/notice_board_write") // 공지사항 글 등록 처리
	public String noticeBoardWrite(NoticeBoardVO vo) {
		
		noticeBoardService.insertNoticeBoard(vo);
		
		return "redirect:noticeBoard";
	}
	
	@RequestMapping("/notice_board_update_form") // 공지사항 글 수정 화면 출력
	public String noticeBoardUpdateForm(NoticeBoardVO vo, Model model, HttpSession session) {
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) { // 관리자 로그인이 안되어 있었을시
			return "redirect:main";
		}
		// 공지사항 글 수정 처리
		NoticeBoardVO noticeBoard = noticeBoardService.getNoticeBoard(vo);
		
		model.addAttribute("noticeBoard", noticeBoard);
		
		return "noticeBoard/noticeBoardUpdateForm";
	}
	
	@RequestMapping("/notice_board_update") // 공지사항 글 수정 처리
	public String noticeBoardUpdate(NoticeBoardVO vo) {
		
		noticeBoardService.updateNoticeBoard(vo);
		
		return "redirect:noticeBoard";
	}
	
	@RequestMapping("/notice_board_delete") // 공지사항 글 삭제 처리
	public String noticeBoardDelete(NoticeBoardVO vo) {
		
		noticeBoardService.deleteNoticeBoard(vo.getNseq());
		
		return "redirect:noticeBoard";
	}
	
}
