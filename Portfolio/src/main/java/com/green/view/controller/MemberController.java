package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.address.AddressService;
import com.green.biz.address.AddressVO;
import com.green.biz.board.BoardService;
import com.green.biz.incomings.IncomingsService;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.member.WorkerVO;
import com.green.biz.outgoings.OutgoingsService;
import com.green.biz.reply.ReplyService;
import com.green.biz.util.Email;
import com.green.biz.util.EmailSender;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private Email email;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private BoardService boardService;
	@Autowired
	private IncomingsService incomingsService;
	@Autowired
	private OutgoingsService outgoingsService;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/login_form") // 로그인 화면 호출
	public String loginMember() {
		
		return "member/loginForm";
	}
	
	@RequestMapping("/login") // 로그인 처리
	public String login(Model model, MemberVO vo, HttpSession session) {
		// 입력한 아이디, 비밀번호로 해당값을 가져옴(1:아이디 o, 비밀번호 o, 0:아이디 o, 비밀번호 틀림, -1:아이디 없음)
		int member = memberService.loginMember(vo.getId(), vo.getPwd());
		// 입력된 아이디로 해당 아이디의 정보를 가져옴
		MemberVO loginMember = memberService.getMember(vo.getId());
		
		if(member == 1) { // 로그인 성공
			model.addAttribute("loginUser", loginMember); // 세션에 회원 정보 저장
			return "redirect:main";
		} else if(member == 0) { // 아이디 존재하고 비밀번호 틀림
			model.addAttribute("message", "비밀번호가 틀렸습니다!");
		} else { // 아이디가 존재하지 않음
			model.addAttribute("message", "아이디가 틀렸습니다!");
		}
		
		return "member/loginForm";
	}
	
	@RequestMapping("/logout") // 로그아웃 처리
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:main";
	}
	
	@RequestMapping("/contract_form") // 회원 가입 약관 화면 호출
	public String contractForm() {
		
		return "member/contract";
	}
	
	@RequestMapping("/insert_member_form") // 회원 가입 화면 호출
	public String insertMemberForm() {
		
		return "member/signForm";
	}
	
	@RequestMapping("/insert_member")
	public String insertMember() {
		
		return "redirect:main";
	}
	
	// 아이디 중복체크 화면 호출
	@RequestMapping(value = "/id_check_form", method = RequestMethod.GET)
	public String idCheck(Model model, MemberVO vo) {
		// 입력된 아이디를 화면에 넘겨줌
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	// 아이디 중복체크 처리
	@RequestMapping(value = "/id_check_form", method = RequestMethod.POST)
	public String idCheck(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMember(vo.getId());
		
		if(member == null) { // 아이디가 존재하지 않음
			model.addAttribute("message", -1);
			model.addAttribute("id", vo.getId());
		} else { // 아이디가 존재함
			model.addAttribute("message", 1);
			model.addAttribute("id", vo.getId());
		}
		
		return "member/idcheck";
	}
	
	@RequestMapping("/addr_search_form") // 주소검색 화면 호출
	public String addrSearchForm() {
		
		return "member/findZipNum";
	}
	
	@RequestMapping("/addr_search_form2") // 관리자 화면에서 주소검색 화면 호출
	public String addrSearchForm2() {
		
		return "member/findZipNum2";
	}
	
	@RequestMapping("/find_zip_num") // 주소 검색 처리
	public String addrFindZipNum(AddressVO vo, Model model) {
		// 동으로 검색한 주소 리스트를 리스트에 저장
		List<AddressVO> address = addressService.addressList(vo.getDong());
		
		model.addAttribute("addressList", address);
		
		return "member/findZipNum";
	}
	
	@RequestMapping("/find_zip_num2") // 관리자 화면에서 회원정보 주소 검색 처리
	public String addrFindZipNum2(AddressVO vo, Model model) {
		
		List<AddressVO> address = addressService.addressList(vo.getDong());
		
		model.addAttribute("addressList", address);
		
		return "member/findZipNum2";
	}
	
	@RequestMapping("/member_join") // 회원 가입 처리
	public String memberJoin(@RequestParam(value = "addr1", required = false) String addr1,
							 @RequestParam(value = "addr2", required = false) String addr2
			, MemberVO vo) {
		
		vo.setAddress(addr1 + " "+ addr2);
		memberService.insertMember(vo);
		
		return "redirect:main";
	}
	
	@RequestMapping("/id_pwd_check_form") // 아이디 및 비밀번호 찾기 페이지 호출
	public String idAndPasswordCheckForm() {
		
		return "member/findIdAndPassword";
	}
	
	@RequestMapping("/find_member_id") // 아이디 찾기 결과창 페이지 호출
	public String findMemberIdForm(MemberVO vo, Model model) {
		// 해당 정보로 멤버 정보를 찾음
		MemberVO member = memberService.findId(vo);
		
		if(member != null) { // 멤버(아이디)가 없을 경우
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else { // 멤버(아이디)가 있는 경우
			model.addAttribute("message", -1);
		}
		
		return "member/findIdResult";
	}
	
	@RequestMapping("/find_member_pwd") // 비밀번호 찾기 결과창 페이지 호출
	public String newPassword(MemberVO vo, Model model) throws Exception {
		// 입력정보를 받아서 회원 정보를 가져옴
		MemberVO member = memberService.findPwd(vo);
		
		if(member != null) { // 회원정보가 있는 경우
			email.setContent("비밀번호는 "+member.getPwd() + "입니다.");
			email.setReceiver(vo.getEmail());
			email.setSubject(vo.getId() + "님 비밀번호 찾기 메일입니다.");
			emailSender.SendEmail(email); // 위의 메세지를 회원의 이메일로 전송
			
			model.addAttribute("message", 1);
			
			return "member/findPwdResult";
		}
		model.addAttribute("message", -1); // 회원정보가 없는 경우
		
		return "member/findPwdResult";
	}
	
	@RequestMapping("/member_delete_form") // 회원 탈퇴 페이지 호출
	public String memberDeleteForm(HttpSession session) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		return "member/memberDelete";
	}
	
	@RequestMapping("/member_delete") // 회원 탈퇴 처리
	public String memberDelete(HttpSession session, SessionStatus status, MemberVO vo, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		// 입력한 비밀번호와 DB의 회원의 비밀번호가 다를때
		if(!vo.getPwd().equals(member.getPwd())) {
			model.addAttribute("message", "비밀번호가 틀렸습니다!");
			
			return "member/memberDelete";
		}
		// 입력한 비밀번호와 DB의 회원의 비밀번호가 같을떄
		// 아이디를 조건으로 DB에 등록되어 있는 5개 테이블 삭제 
		boardService.deleteBoardById(member.getId());
		outgoingsService.outgoingsDeleteById(member.getId());
		incomingsService.incomingsDeleteById(member.getId());
		replyService.deleteReplyById(member.getId());
		memberService.deleteMember(member.getId());
		
		status.setComplete();
		
		return "main";
	}
	
}
