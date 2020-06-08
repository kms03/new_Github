package com.green.view.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.incomings.IncomingsService;
import com.green.biz.incomings.IncomingsVO;
import com.green.biz.member.MemberVO;
import com.green.biz.member.WorkerVO;
import com.green.biz.outgoings.OutgoingsService;
import com.green.biz.outgoings.OutgoingsVO;

@Controller
public class RegisterController {
	@Autowired
	private OutgoingsService outgoingsService;
	@Autowired
	private IncomingsService incomingsService;
	
	static int cnt = 0;
	static int cnt_1 = 0;
	
	@RequestMapping("/register_main")
	public String registerMain(HttpSession session, Model model, 
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "searchType", defaultValue = "o_use") String searchType,
			@RequestParam(value = "keyword1", defaultValue = "", required = false) String keyword1,
			@RequestParam(value = "searchType1", defaultValue = "i_use", required = false)String searchType1) throws Exception {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt = 0; cnt_1 = 0;
		
		OutgoingsVO vo = new OutgoingsVO();
		IncomingsVO ivo = new IncomingsVO();
		
		ivo.setId(member.getId());
		ivo.setSearchType(searchType1);
		ivo.setKeyword(keyword1);
		
		vo.setKeyword(keyword);
		vo.setId(member.getId());
		vo.setSearchType(searchType);
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);	
		vo.setId(member.getId());	
		
		List<OutgoingsVO> list = outgoingsService.getOutgoingsList(vo);
		List<IncomingsVO> incomingsList = incomingsService.incomingsList(ivo);
		
		model.addAttribute("incomingsList", incomingsList);
		model.addAttribute("outgoingsList", list);
		
		int in_sum = 0;
		for(IncomingsVO incoming : incomingsList) {
			in_sum += incoming.getIprice();
		}
		
		int out_sum = 0;
		for(OutgoingsVO going : list) {
			out_sum += going.getOprice();
		}
		
		model.addAttribute("in_sum", in_sum);
		model.addAttribute("out_sum", out_sum);
		
		return "register/registerMain";

	}

	@RequestMapping("/prev_month")
	public String prevMonth(HttpSession session, Model model, OutgoingsVO vo, IncomingsVO in,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "searchType", defaultValue = "o_use") String searchType,
			@RequestParam(value = "keyword1", defaultValue = "", required = false) String keyword1,
			@RequestParam(value = "searchType1", defaultValue = "i_use", required = false)String searchType1) throws Exception {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt -= 1; cnt_1 -= 1;
		
		in.setId(member.getId());
		in.setKeyword(keyword1);
		in.setSearchType(searchType1);
		
		vo.setKeyword(keyword);
		vo.setId(member.getId());
		vo.setSearchType(searchType);
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt+1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		List<OutgoingsVO> prev = outgoingsService.prevMonth(vo);
		
		in.setI_date(d);
		in.setId(member.getId());
		List<IncomingsVO> incomingsList = incomingsService.incomingsPrevMonth(in);
		
		model.addAttribute("incomingsList", incomingsList);
		model.addAttribute("outgoingsList", prev);
		
		int out_sum = 0;
		for(OutgoingsVO going : prev) {
			out_sum += going.getOprice();
		}
		
		int in_sum = 0;
		for(IncomingsVO incomings : incomingsList) {
			in_sum += incomings.getIprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		model.addAttribute("in_sum", in_sum);
		
		return "register/registerPrev";
	}
	
	@RequestMapping("/prev_month_search")
	public String prevMonthSearch(HttpSession session, Model model, OutgoingsVO vo, IncomingsVO in,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "searchType", defaultValue = "o_use") String searchType,
			@RequestParam(value = "keyword1", defaultValue = "", required = false) String keyword1,
			@RequestParam(value = "searchType1", defaultValue = "i_use", required = false)String searchType1) throws Exception {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
				
		in.setId(member.getId());
		in.setKeyword(keyword1);
		in.setSearchType(searchType1);
		
		vo.setKeyword(keyword);
		vo.setId(member.getId());
		vo.setSearchType(searchType);
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt+1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		List<OutgoingsVO> prev = outgoingsService.prevMonth(vo);
		
		in.setI_date(d);
		in.setId(member.getId());
		List<IncomingsVO> incomingsList = incomingsService.incomingsPrevMonth(in);
		
		model.addAttribute("incomingsList", incomingsList);
		model.addAttribute("outgoingsList", prev);
		
		int out_sum = 0;
		for(OutgoingsVO going : prev) {
			out_sum += going.getOprice();
		}
		
		int in_sum = 0;
		for(IncomingsVO incomings : incomingsList) {
			in_sum += incomings.getIprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		model.addAttribute("in_sum", in_sum);
		
		return "register/registerPrev";
	}
	
	@RequestMapping("/prev_month_re")
	public String prevMonthRe(HttpSession session, Model model, OutgoingsVO vo) {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt -= 1; cnt_1 -= 1;
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt+1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		List<OutgoingsVO> prev = outgoingsService.prevMonth(vo);

		model.addAttribute("outgoingsList", prev);
		System.out.println(d);
		
		int out_sum = 0;
		for(OutgoingsVO going : prev) {
			out_sum += going.getOprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		
		return "register/registerPrev";
	}
	
	@RequestMapping("/next_month")
	public String nextMonth(HttpSession session, Model model, OutgoingsVO vo, IncomingsVO in) {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt += 1; cnt_1 += 1;
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt-1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		
		in.setI_date(d);
		in.setId(member.getId());
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsNextMonth(in);
		
		List<OutgoingsVO> next = outgoingsService.nextMonth(vo);
		
		model.addAttribute("incomingsList", incomingsList);
		model.addAttribute("outgoingsList", next);
		
		int out_sum = 0;
		for(OutgoingsVO going : next) {
			out_sum += going.getOprice();
		}
		
		int in_sum = 0;
		for(IncomingsVO incomings : incomingsList) {
			in_sum += incomings.getIprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		model.addAttribute("in_sum", in_sum);
		
		return "register/registerNext";
	}
	
	@RequestMapping("/next_month_search")
	public String nextMonthSearch(HttpSession session, Model model, OutgoingsVO vo, IncomingsVO in,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "searchType", defaultValue = "o_use") String searchType,
			@RequestParam(value = "keyword1", defaultValue = "", required = false) String keyword1,
			@RequestParam(value = "searchType1", defaultValue = "i_use", required = false)String searchType1) throws Exception {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		in.setId(member.getId());
		in.setKeyword(keyword1);
		in.setSearchType(searchType1);
		
		vo.setKeyword(keyword);
		vo.setId(member.getId());
		vo.setSearchType(searchType);
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt-1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		
		in.setI_date(d);
		in.setId(member.getId());
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsNextMonth(in);
		
		List<OutgoingsVO> next = outgoingsService.nextMonth(vo);
		
		model.addAttribute("incomingsList", incomingsList);
		model.addAttribute("outgoingsList", next);
		
		int out_sum = 0;
		for(OutgoingsVO going : next) {
			out_sum += going.getOprice();
		}
		
		int in_sum = 0;
		for(IncomingsVO incomings : incomingsList) {
			in_sum += incomings.getIprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		model.addAttribute("in_sum", in_sum);
		
		return "register/registerNext";
	}
	
	@RequestMapping("/next_month_re")
	public String nextMonthRe(HttpSession session, Model model, OutgoingsVO vo) {

		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt += 1; cnt_1 += 1;
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, cnt-1);
		cal2.add(Calendar.MONTH, cnt_1);
		Date d = new Date(cal.getTimeInMillis());
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		model.addAttribute("date", simpledate.format(cal2.getTime()));
		
		vo.setO_date(d);
		vo.setId(member.getId());
		
		List<OutgoingsVO> next = outgoingsService.nextMonth(vo);

		model.addAttribute("outgoingsList", next);
		
		int out_sum = 0;
		for(OutgoingsVO going : next) {
			out_sum += going.getOprice();
		}
		
		model.addAttribute("out_sum", out_sum);
		
		return "register/registerPrev";
	}
	
	
	@RequestMapping("/register_write_form")
	public String registerWriteForm(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		String[] o_category = {"식비", "주거/통신", "생활용품", "의복/미용", "건강/문화", "교육/육아", "교통/차량", "경조사/회비", "세금/이자", "용돈/기타"};
		
		model.addAttribute("o_category", o_category);
		
		return "register/registerWriteForm";
	}
	
	@RequestMapping("/register_write")
	public String registerWrite(OutgoingsVO vo, HttpSession session) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		vo.setId(member.getId());
		
		outgoingsService.insertOutgoings(vo);
		
		return "redirect:register_main";
	}
	
	@RequestMapping("/in_write_form")
	public String incomingsWriteForm(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		String[] i_category = {"주수입", "부수입", "전월이월", "저축/보험"};
		
		model.addAttribute("i_category", i_category);
		
		return "register/registerInWriteForm";
	}
	
	@RequestMapping("/incomings_write")
	public String incomingsWrite(HttpSession session, IncomingsVO vo) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		vo.setId(member.getId());
		incomingsService.insertIncomings(vo);
		
		return "redirect:register_main";
	}
	
	@RequestMapping("/register_incomings_delete")
	public String incomingsDelete(HttpSession session, IncomingsVO vo,
			@RequestParam(value = "chbox") List<String> chbox) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		vo.setId(member.getId());
		System.out.println(vo);
		
		for(int i=0; i<chbox.size(); i++) {
			if(chbox.size() == 1) {
				vo.setIseq(Integer.parseInt(chbox.get(i)));
				incomingsService.incomingsDelete(vo);
			}
		}
		
		return "redirect:register_main";
	}
	
	@RequestMapping("/register_outgoings_delete")
	public String outgoingsDelete(HttpSession session, OutgoingsVO vo,
			@RequestParam(value = "chbox") List<String> chbox) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		vo.setId(member.getId());
		for(int i=0; i<chbox.size(); i++) {
			if(chbox.size() == 1) {
				vo.setOseq(Integer.parseInt(chbox.get(i)));
				outgoingsService.outgoingsDelete(vo);
			}
		}
		
		return "redirect:register_main";
	}
	
}
