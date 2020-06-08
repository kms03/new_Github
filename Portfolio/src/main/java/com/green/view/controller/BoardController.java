package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.biz.board.BoardService;
import com.green.biz.board.BoardVO;
import com.green.biz.member.MemberVO;
import com.green.biz.member.WorkerVO;
import com.green.biz.reply.ReplyService;
import com.green.biz.reply.ReplyVO;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/board") // 자유 게시판 리스트 출력
	public String getBoardList(Model model, BoardVO vo, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		// 자유 게시판 리스트 정보
		List<BoardVO> boardList = boardService.boardList(scri);
		// 게시판 페이징 처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.listCount(scri));
		// 게시판 리스트 정보와 페이징 처리된 번호를 화면에 넘겨준다.
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/BoardList";
	}
	
	@RequestMapping("/board_write_form") // 게시판 글쓰기 화면으로 이동
	public String boardListForm(HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) { // 사용자 및 관리자 로그인 안돼 있을시 로그인 화면으로 이동 
			return "member/loginForm";
		}
		
		return "board/boardWriteForm";
	}
	
	@RequestMapping("/board_write") // 게시판 글쓰기 등록
	public String boardWrite(BoardVO vo) {
		
		boardService.insertBoard(vo);
		
		return "redirect:board";
	}
	
	@RequestMapping("/board_detail") // 게시글 상세보기
	public String boardDetail(BoardVO vo, Model model,
			@ModelAttribute("scri") SearchCriteria scri, HttpSession session) throws Exception {
		
		boardService.updateCnt(vo.getBseq()); // 상세보기 화면으로 들어갔을때 조회수 +1씩 업데이트
		// 해당 게시글 bseq로 해당 게시글 정보를 가져옴
		BoardVO board = boardService.getBoard(vo.getBseq());
		// 해당 게시글 정보를 화면에 넘겨줌
		model.addAttribute("board", board);
		// 상세보기 화면안에 있는 댓글 페이징 처리
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(replyService.replyCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		// 상세 화면 안에 있는 댓글 정보를 리스트로 가져옴
		List<ReplyVO> replyList = replyService.boardReplyList(scri);
		
		model.addAttribute("replyList", replyList);
		
		return "board/boardDetail";
	}
	
	@RequestMapping("/board_update_form") // 게시글 수정 페이지 화면으로 이동
	public String boardUpdateForm(Model model, BoardVO vo) {
		
		BoardVO board = boardService.getBoard(vo.getBseq());
		
		model.addAttribute("board", board);
		
		return "board/boardUpdateForm";
	}
	
	@RequestMapping("/board_update") // 게시글 수정 처리
	public String boardUpdate(BoardVO vo) {
		
		boardService.updateBoard(vo);
		// 해당 게시글 번호를 넘겨줘서 상세화면을 재호출한다.
		return "redirect:board_detail?bseq="+vo.getBseq();
	}
	
	@RequestMapping("/board_delete") // 게시글 삭제 처리
	public String boardDelete(BoardVO vo) {
		
		boardService.deleteBoard(vo);
		
		return "redirect:board";
	}
	
	@RequestMapping("/insert_reply") // 댓글 등록 처리
	public String insertReply(ReplyVO vo, HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		vo.setId(member.getId());
		
		replyService.insertReply(vo);	
		
		return "redirect:board_detail?bseq="+vo.getBseq();
	}
	
	@RequestMapping("/reply_update_form") // 댓글 수정 입력창 호출
	public String updateReplyForm(ReplyVO vo, Model model) {
		
		ReplyVO reply = replyService.getReply(vo.getRno()); // 댓글 번호로 댓글vo 추출
		
		model.addAttribute("reply", reply);
		
		return "board/comment";
	}
	
	
	@RequestMapping("/reply_update") // 댓글 수정
	public String updateReply(ReplyVO vo, Model model) {
		
		replyService.updateReply(vo); // 댓글 수정처리
		
		model.addAttribute("message", 1); // 메세지 화면에 보내줌
		
		return "board/comment"; // 댓글 수정 페이지로 이동
	}
	
	@RequestMapping("/reply_delete") // 댓글 삭제
	public String deleteReply(ReplyVO vo) {
		
		replyService.deleteReply(vo); // 댓글 삭제 처리
		
		return "redirect:board_detail?bseq="+vo.getBseq(); // 게시판 상세화면으로 재호출
	}
	
	@RequestMapping("/save_comment1") // 댓글 입력
	public String saveComment1(HttpSession session, ReplyVO vo) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		// 관리자 로그인 및 사용자 로그인이 안돼 있으면 로그인 화면으로 이동
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		if(member != null) { // 사용자가 로그인했을 경우
			vo.setId(member.getId());
			replyService.insertReply(vo); // 댓글 등록처리
		}
		
		if(worker != null) { // 관리자가 로그인 했을 경우
			vo.setId(worker.getId());
			replyService.insertReply(vo); // 댓글 등록 처리
		}
		
		return "redirect:board_detail?bseq="+vo.getBseq(); // 게시판 상세화면으로 재호출
	}
	
}


