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
	
	@RequestMapping("/login_form") // �α��� ȭ�� ȣ��
	public String loginMember() {
		
		return "member/loginForm";
	}
	
	@RequestMapping("/login") // �α��� ó��
	public String login(Model model, MemberVO vo, HttpSession session) {
		// �Է��� ���̵�, ��й�ȣ�� �ش簪�� ������(1:���̵� o, ��й�ȣ o, 0:���̵� o, ��й�ȣ Ʋ��, -1:���̵� ����)
		int member = memberService.loginMember(vo.getId(), vo.getPwd());
		// �Էµ� ���̵�� �ش� ���̵��� ������ ������
		MemberVO loginMember = memberService.getMember(vo.getId());
		
		if(member == 1) { // �α��� ����
			model.addAttribute("loginUser", loginMember); // ���ǿ� ȸ�� ���� ����
			return "redirect:main";
		} else if(member == 0) { // ���̵� �����ϰ� ��й�ȣ Ʋ��
			model.addAttribute("message", "��й�ȣ�� Ʋ�Ƚ��ϴ�!");
		} else { // ���̵� �������� ����
			model.addAttribute("message", "���̵� Ʋ�Ƚ��ϴ�!");
		}
		
		return "member/loginForm";
	}
	
	@RequestMapping("/logout") // �α׾ƿ� ó��
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:main";
	}
	
	@RequestMapping("/contract_form") // ȸ�� ���� ��� ȭ�� ȣ��
	public String contractForm() {
		
		return "member/contract";
	}
	
	@RequestMapping("/insert_member_form") // ȸ�� ���� ȭ�� ȣ��
	public String insertMemberForm() {
		
		return "member/signForm";
	}
	
	@RequestMapping("/insert_member")
	public String insertMember() {
		
		return "redirect:main";
	}
	
	// ���̵� �ߺ�üũ ȭ�� ȣ��
	@RequestMapping(value = "/id_check_form", method = RequestMethod.GET)
	public String idCheck(Model model, MemberVO vo) {
		// �Էµ� ���̵� ȭ�鿡 �Ѱ���
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	// ���̵� �ߺ�üũ ó��
	@RequestMapping(value = "/id_check_form", method = RequestMethod.POST)
	public String idCheck(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMember(vo.getId());
		
		if(member == null) { // ���̵� �������� ����
			model.addAttribute("message", -1);
			model.addAttribute("id", vo.getId());
		} else { // ���̵� ������
			model.addAttribute("message", 1);
			model.addAttribute("id", vo.getId());
		}
		
		return "member/idcheck";
	}
	
	@RequestMapping("/addr_search_form") // �ּҰ˻� ȭ�� ȣ��
	public String addrSearchForm() {
		
		return "member/findZipNum";
	}
	
	@RequestMapping("/addr_search_form2") // ������ ȭ�鿡�� �ּҰ˻� ȭ�� ȣ��
	public String addrSearchForm2() {
		
		return "member/findZipNum2";
	}
	
	@RequestMapping("/find_zip_num") // �ּ� �˻� ó��
	public String addrFindZipNum(AddressVO vo, Model model) {
		// ������ �˻��� �ּ� ����Ʈ�� ����Ʈ�� ����
		List<AddressVO> address = addressService.addressList(vo.getDong());
		
		model.addAttribute("addressList", address);
		
		return "member/findZipNum";
	}
	
	@RequestMapping("/find_zip_num2") // ������ ȭ�鿡�� ȸ������ �ּ� �˻� ó��
	public String addrFindZipNum2(AddressVO vo, Model model) {
		
		List<AddressVO> address = addressService.addressList(vo.getDong());
		
		model.addAttribute("addressList", address);
		
		return "member/findZipNum2";
	}
	
	@RequestMapping("/member_join") // ȸ�� ���� ó��
	public String memberJoin(@RequestParam(value = "addr1", required = false) String addr1,
							 @RequestParam(value = "addr2", required = false) String addr2
			, MemberVO vo) {
		
		vo.setAddress(addr1 + " "+ addr2);
		memberService.insertMember(vo);
		
		return "redirect:main";
	}
	
	@RequestMapping("/id_pwd_check_form") // ���̵� �� ��й�ȣ ã�� ������ ȣ��
	public String idAndPasswordCheckForm() {
		
		return "member/findIdAndPassword";
	}
	
	@RequestMapping("/find_member_id") // ���̵� ã�� ���â ������ ȣ��
	public String findMemberIdForm(MemberVO vo, Model model) {
		// �ش� ������ ��� ������ ã��
		MemberVO member = memberService.findId(vo);
		
		if(member != null) { // ���(���̵�)�� ���� ���
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else { // ���(���̵�)�� �ִ� ���
			model.addAttribute("message", -1);
		}
		
		return "member/findIdResult";
	}
	
	@RequestMapping("/find_member_pwd") // ��й�ȣ ã�� ���â ������ ȣ��
	public String newPassword(MemberVO vo, Model model) throws Exception {
		// �Է������� �޾Ƽ� ȸ�� ������ ������
		MemberVO member = memberService.findPwd(vo);
		
		if(member != null) { // ȸ�������� �ִ� ���
			email.setContent("��й�ȣ�� "+member.getPwd() + "�Դϴ�.");
			email.setReceiver(vo.getEmail());
			email.setSubject(vo.getId() + "�� ��й�ȣ ã�� �����Դϴ�.");
			emailSender.SendEmail(email); // ���� �޼����� ȸ���� �̸��Ϸ� ����
			
			model.addAttribute("message", 1);
			
			return "member/findPwdResult";
		}
		model.addAttribute("message", -1); // ȸ�������� ���� ���
		
		return "member/findPwdResult";
	}
	
	@RequestMapping("/member_delete_form") // ȸ�� Ż�� ������ ȣ��
	public String memberDeleteForm(HttpSession session) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		return "member/memberDelete";
	}
	
	@RequestMapping("/member_delete") // ȸ�� Ż�� ó��
	public String memberDelete(HttpSession session, SessionStatus status, MemberVO vo, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		// �Է��� ��й�ȣ�� DB�� ȸ���� ��й�ȣ�� �ٸ���
		if(!vo.getPwd().equals(member.getPwd())) {
			model.addAttribute("message", "��й�ȣ�� Ʋ�Ƚ��ϴ�!");
			
			return "member/memberDelete";
		}
		// �Է��� ��й�ȣ�� DB�� ȸ���� ��й�ȣ�� ������
		// ���̵� �������� DB�� ��ϵǾ� �ִ� 5�� ���̺� ���� 
		boardService.deleteBoardById(member.getId());
		outgoingsService.outgoingsDeleteById(member.getId());
		incomingsService.incomingsDeleteById(member.getId());
		replyService.deleteReplyById(member.getId());
		memberService.deleteMember(member.getId());
		
		status.setComplete();
		
		return "main";
	}
	
}
