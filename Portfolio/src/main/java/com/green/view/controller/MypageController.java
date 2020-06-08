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

	@RequestMapping(value = "/mypage") // 마이페이지 화면 호출
	public String mypage(HttpSession session, Model model, MemberVO vo) {

		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO) session.getAttribute("adminUser");
		// 사용자 및 관리자 미로그인시 로그인화면 호출
		if (member == null && worker == null) {
			return "member/loginForm";
		}
		// id를 조건으로 사용자 정보를 가져옴
		MemberVO user = memberService.getMember(member.getId());

		model.addAttribute("member", user);

		return "mypage/mypageMain";
	}

	@RequestMapping("/mypage_member_update") // 회원 정보 수정 처리
	public String mypageMemberUpdate(MemberVO vo, Model model, HttpSession session,
			@RequestParam(value = "new_pwd", required = false) String new_pwd, SessionStatus status) {

		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO) session.getAttribute("adminUser");
		// 사용자 및 관리자 미로그인시 로그인화면 호출
		if (member == null && worker == null) {
			return "member/loginForm";
		}
		// 입력한 비밀번호와 DB의 사용자 비밀번호가 다를 경우
		if (!member.getPwd().equals(vo.getPwd())) {
			model.addAttribute("member", member);
			model.addAttribute("message1", "비밀번호가 틀렸습니다!");

			return "mypage/mypageMain";
		}
		
		if (new_pwd == "") { // 새로운 비밀번호가 입력 안된 경우
			vo.setId(member.getId());
			memberService.updateMember(vo); // 비밀번호 제외 나머지 정보 업데이트
			// 업데이트 후 멤버의 새 정보를 가져온다.
			MemberVO re_member = memberService.getMember(member.getId());
			
			model.addAttribute("message2", "수정되었습니다!");
			model.addAttribute("member", re_member);

			return "mypage/mypageMain";

		} else { // 새로운 비밀번호가 입력된 경우
			
			vo.setPwd(new_pwd);
			memberService.updatePwd(vo); // 비밀번호를 재설정한다.
			// 세션을 삭제한다.
			status.setComplete();

		}

		return "redirect:main";

	}

}
