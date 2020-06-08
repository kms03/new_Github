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
	
	@RequestMapping("/board") // ���� �Խ��� ����Ʈ ���
	public String getBoardList(Model model, BoardVO vo, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		// ���� �Խ��� ����Ʈ ����
		List<BoardVO> boardList = boardService.boardList(scri);
		// �Խ��� ����¡ ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.listCount(scri));
		// �Խ��� ����Ʈ ������ ����¡ ó���� ��ȣ�� ȭ�鿡 �Ѱ��ش�.
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/BoardList";
	}
	
	@RequestMapping("/board_write_form") // �Խ��� �۾��� ȭ������ �̵�
	public String boardListForm(HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) { // ����� �� ������ �α��� �ȵ� ������ �α��� ȭ������ �̵� 
			return "member/loginForm";
		}
		
		return "board/boardWriteForm";
	}
	
	@RequestMapping("/board_write") // �Խ��� �۾��� ���
	public String boardWrite(BoardVO vo) {
		
		boardService.insertBoard(vo);
		
		return "redirect:board";
	}
	
	@RequestMapping("/board_detail") // �Խñ� �󼼺���
	public String boardDetail(BoardVO vo, Model model,
			@ModelAttribute("scri") SearchCriteria scri, HttpSession session) throws Exception {
		
		boardService.updateCnt(vo.getBseq()); // �󼼺��� ȭ������ ������ ��ȸ�� +1�� ������Ʈ
		// �ش� �Խñ� bseq�� �ش� �Խñ� ������ ������
		BoardVO board = boardService.getBoard(vo.getBseq());
		// �ش� �Խñ� ������ ȭ�鿡 �Ѱ���
		model.addAttribute("board", board);
		// �󼼺��� ȭ��ȿ� �ִ� ��� ����¡ ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(replyService.replyCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		// �� ȭ�� �ȿ� �ִ� ��� ������ ����Ʈ�� ������
		List<ReplyVO> replyList = replyService.boardReplyList(scri);
		
		model.addAttribute("replyList", replyList);
		
		return "board/boardDetail";
	}
	
	@RequestMapping("/board_update_form") // �Խñ� ���� ������ ȭ������ �̵�
	public String boardUpdateForm(Model model, BoardVO vo) {
		
		BoardVO board = boardService.getBoard(vo.getBseq());
		
		model.addAttribute("board", board);
		
		return "board/boardUpdateForm";
	}
	
	@RequestMapping("/board_update") // �Խñ� ���� ó��
	public String boardUpdate(BoardVO vo) {
		
		boardService.updateBoard(vo);
		// �ش� �Խñ� ��ȣ�� �Ѱ��༭ ��ȭ���� ��ȣ���Ѵ�.
		return "redirect:board_detail?bseq="+vo.getBseq();
	}
	
	@RequestMapping("/board_delete") // �Խñ� ���� ó��
	public String boardDelete(BoardVO vo) {
		
		boardService.deleteBoard(vo);
		
		return "redirect:board";
	}
	
	@RequestMapping("/insert_reply") // ��� ��� ó��
	public String insertReply(ReplyVO vo, HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		vo.setId(member.getId());
		
		replyService.insertReply(vo);	
		
		return "redirect:board_detail?bseq="+vo.getBseq();
	}
	
	@RequestMapping("/reply_update_form") // ��� ���� �Է�â ȣ��
	public String updateReplyForm(ReplyVO vo, Model model) {
		
		ReplyVO reply = replyService.getReply(vo.getRno()); // ��� ��ȣ�� ���vo ����
		
		model.addAttribute("reply", reply);
		
		return "board/comment";
	}
	
	
	@RequestMapping("/reply_update") // ��� ����
	public String updateReply(ReplyVO vo, Model model) {
		
		replyService.updateReply(vo); // ��� ����ó��
		
		model.addAttribute("message", 1); // �޼��� ȭ�鿡 ������
		
		return "board/comment"; // ��� ���� �������� �̵�
	}
	
	@RequestMapping("/reply_delete") // ��� ����
	public String deleteReply(ReplyVO vo) {
		
		replyService.deleteReply(vo); // ��� ���� ó��
		
		return "redirect:board_detail?bseq="+vo.getBseq(); // �Խ��� ��ȭ������ ��ȣ��
	}
	
	@RequestMapping("/save_comment1") // ��� �Է�
	public String saveComment1(HttpSession session, ReplyVO vo) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		// ������ �α��� �� ����� �α����� �ȵ� ������ �α��� ȭ������ �̵�
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		if(member != null) { // ����ڰ� �α������� ���
			vo.setId(member.getId());
			replyService.insertReply(vo); // ��� ���ó��
		}
		
		if(worker != null) { // �����ڰ� �α��� ���� ���
			vo.setId(worker.getId());
			replyService.insertReply(vo); // ��� ��� ó��
		}
		
		return "redirect:board_detail?bseq="+vo.getBseq(); // �Խ��� ��ȭ������ ��ȣ��
	}
	
}


