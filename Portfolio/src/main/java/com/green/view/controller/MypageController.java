package com.green.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;
import com.green.biz.member.WorkerVO;

@Controller
public class MypageController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/mypage") // ���������� ȭ�� ȣ��
	public String mypage(HttpSession session, Model model, MemberVO vo) {

		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO) session.getAttribute("adminUser");
		// ����� �� ������ �̷α��ν� �α���ȭ�� ȣ��
		if (member == null && worker == null) {
			return "member/loginForm";
		}
		// id�� �������� ����� ������ ������
		MemberVO user = memberService.getMember(member.getId());

		model.addAttribute("member", user);

		return "mypage/mypageMain";
	}

	@RequestMapping("/mypage_member_update") // ȸ�� ���� ���� ó��
	public String mypageMemberUpdate(MemberVO vo, Model model, HttpSession session,
			@RequestParam(value = "new_pwd", required = false) String new_pwd, SessionStatus status) {

		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO) session.getAttribute("adminUser");
		// ����� �� ������ �̷α��ν� �α���ȭ�� ȣ��
		if (member == null && worker == null) {
			return "member/loginForm";
		}
		// �Է��� ��й�ȣ�� DB�� ����� ��й�ȣ�� �ٸ� ���
		if (!member.getPwd().equals(vo.getPwd())) {
			model.addAttribute("member", member);
			model.addAttribute("message1", "��й�ȣ�� Ʋ�Ƚ��ϴ�!");

			return "mypage/mypageMain";
		}
		
		if (new_pwd == "") { // ���ο� ��й�ȣ�� �Է� �ȵ� ���
			vo.setId(member.getId());
			memberService.updateMember(vo); // ��й�ȣ ���� ������ ���� ������Ʈ
			// ������Ʈ �� ����� �� ������ �����´�.
			MemberVO re_member = memberService.getMember(member.getId());
			
			model.addAttribute("message2", "�����Ǿ����ϴ�!");
			model.addAttribute("member", re_member);

			return "mypage/mypageMain";

		} else { // ���ο� ��й�ȣ�� �Էµ� ���
			
			vo.setPwd(new_pwd);
			memberService.updatePwd(vo); // ��й�ȣ�� �缳���Ѵ�.
			// ������ �����Ѵ�.
			status.setComplete();

		}

		return "redirect:main";

	}

}
