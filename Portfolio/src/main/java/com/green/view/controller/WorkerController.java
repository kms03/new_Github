package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.board.BoardService;
import com.green.biz.board.BoardVO;
import com.green.biz.incomings.IncomingsService;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.member.WorkerService;
import com.green.biz.member.WorkerVO;
import com.green.biz.outgoings.OutgoingsService;
import com.green.biz.reply.ReplyService;
import com.green.biz.reply.ReplyVO;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@SessionAttributes("adminUser")
@Controller
public class WorkerController {
	@Autowired
	private WorkerService workerService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private OutgoingsService outgoingsService; 
	@Autowired
	private IncomingsService incomingsService;
	@Autowired
	private ReplyService replyService;
	
	
	@RequestMapping("/admin_login_form") // 관리자 로그인 화면 출력
	public String adminLoginForm() {
		
		return "admin/adminLogin";
	}
	
	@RequestMapping("/admin_login_check")
	public String adminLoginCheck(WorkerVO vo, HttpSession session, Model model) {
		
		int admincheck = workerService.loginAdmin(vo.getId(), vo.getPwd());
		WorkerVO worker = workerService.getWorker(vo.getId());
		
		if(admincheck == 1) {
			session.setAttribute("adminUser", worker);
			return "redirect:main";
		} else if(admincheck == 0) {
			model.addAttribute("message", "비밀번호가 틀렸습니다!");
		} else {
			model.addAttribute("message", "아이디가 틀렸습니다!");
		}
		
		return "admin/adminLogin";
	}
	
	@RequestMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:main";
	}
	
	@RequestMapping("/member_list")
	public String memberList(HttpSession session, Model model,
			@ModelAttribute("scri") SearchCriteria scri) throws Exception {
		
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) {
			return "redirect:main";
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(memberService.memberCount(scri));
		
		List<MemberVO> memberList = memberService.memberList(scri);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/member/MemberList";
	}
	
	@RequestMapping("/admin_member_detail")
	public String adminMemberDetail(MemberVO vo, Model model, HttpSession session) {
		
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) {
			return "main";
		}
		
		MemberVO member = memberService.getMember(vo.getId());
		
		model.addAttribute("member", member);
		
		return "admin/member/adminMemberDetail";
	}
	
	@RequestMapping("/admin_member_update")
	public String adminMemberUpdate(MemberVO vo) {
		
		memberService.updateMember(vo);
		
		return "redirect:member_list";
	}
	
	@RequestMapping("/admin_member_out")
	public String adminMemberOut(Model model, HttpSession session, WorkerVO vo, SessionStatus status,
			@RequestParam(value = "id")String id) {
		
		WorkerVO admin = (WorkerVO)session.getAttribute("adminUser");
		
		if(!vo.getPwd().equals(admin.getPwd())) {
			MemberVO member = memberService.getMember(id);
			
			model.addAttribute("member", member);
			model.addAttribute("message", "비밀번호가 틀렸습니다!");
			
			return "admin/member/adminMemberDetail";
		}
		
		boardService.deleteBoardById(id);
		outgoingsService.outgoingsDeleteById(id);
		incomingsService.incomingsDeleteById(id);
		replyService.deleteReplyById(id);
		memberService.deleteMember(id);
		
		return "redirect:member_list";
	}
	
	@RequestMapping("/admin_board")
	public String getBoardList(Model model, BoardVO vo, @ModelAttribute("scri") SearchCriteria scri,
			HttpSession session) throws Exception {
		
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) {
			return "main";
		}
		
		List<BoardVO> boardList = boardService.boardList(scri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.listCount(scri));
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/board/adminBoardList";
	}
	
	@RequestMapping("/admin_board_detail")
	public String boardDetail(BoardVO vo, Model model,
			@ModelAttribute("scri") SearchCriteria scri, HttpSession session) throws Exception {
		
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) {
			return "main";
		}
		
		boardService.updateCnt(vo.getBseq());
		
		BoardVO board = boardService.getBoard(vo.getBseq());
		
		model.addAttribute("board", board);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(replyService.replyCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		List<ReplyVO> replyList = replyService.boardReplyList(scri);
		
		model.addAttribute("replyList", replyList);
		
		return "admin/board/adminBoardDetail";
	}
	
	@RequestMapping("/admin_board_write_form")
	public String boardListForm(HttpSession session) {
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) {
			return "main";
		}
		
		return "admin/board/adminBoardWriteForm";
	}
	
	@RequestMapping("/admin_board_write")
	public String boardWrite(BoardVO vo) {
		
		boardService.insertBoard(vo);
		
		return "redirect:admin_board";
	}
	
}
