package com.green.view.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class ReferenceController {
	@Autowired
	private IncomingsService incomingsService;
	@Autowired
	private OutgoingsService outgoingsService;

	static int cnt = 0; // 총 지출 금액 구하기 위한 변수
	static int cnt_2 = 0; // 총 수입 금액 구하기 위한 변수
	static int cnt_3 = 0; // 전 달 및 다음 달 이동을 위한 변수
	static int cnt_5 = 0; // 총 지출 및 수입 금액 구하기 위한 변수
	static int cnt_6 = 0; // 차트의 12달을 구하기 위한 변수

	@RequestMapping("/reference") // 현재 월의 조회 페이지
	public String referenceMain(HttpSession session, Model model) {

		MemberVO member = (MemberVO)session.getAttribute("loginUser"); // 사용자 정보를 세션에 저장
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser"); // 관리자 정보를 세션에 저장
		
		if(member == null && worker == null) { // 사용자 로그인 또는 관리자 로그인이 안된 상태면 로그인 창으로 이동
			return "member/loginForm";
		}
		cnt = 0; cnt_2 = 0; cnt_3 = 0; cnt_5 = 0; cnt_6 = 0;
		
		Date realdate = new Date(Calendar.getInstance().getTimeInMillis()); 
		model.addAttribute("realdate", realdate); // 페이지에 현재 날짜를 넘겨줌

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal.add(Calendar.MONTH, 0);
		cal2.add(Calendar.MONTH, 0);
		Date d = new Date(cal.getTimeInMillis()); // 현재 시간의 날짜를 가지고 있음
		// 조회 메인화면에 yyyy.MM 형식으로 날짜 출력하기 위한 SimpleDateFormat
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>(); // 바차트 x축의 12개의 월을 담기 위한 List
		for (int i = 0; i < 12; i++) {
			if (i == 0) { // 현재 월에 해당하는 날짜를 리스트에 추가한다.
				cal2.add(Calendar.MONTH, 0);
				years.add(simpledate.format(cal2.getTime()));
			} else { // 현재 월-1 순으로 해서 -11월까지 리스트에 추가한다.
				cal2.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal2.getTime()));
			}
		}
		
		model.addAttribute("date", years); // 12개월의 리스트를 화면에 넘겨줌
			
		OutgoingsVO vo = new OutgoingsVO(); // 해당 월의 지출을 구하기 위한 vo객체
		IncomingsVO ivo = new IncomingsVO(); // 해당 월의 수입을 구하기 위한 vo객체
		
		vo.setO_date(d); // 지출 vo에 현재 날짜를 넣는다.
		vo.setId(member.getId()); // 지출 vo에 아이디를 넣는다.
		
		ivo.setI_date(d); // 수입 vo에 현재 날짜를 넣는다.
		ivo.setId(member.getId()); // 수입 vo에 아이디를 넣는다.
		
		// 입력된 아이디와 날짜로 현재의 월에 지출 리스트를 가져온다.
		List<OutgoingsVO> list = outgoingsService.getOutgoingsList(vo);
		// 입력된 아이디와 날짜로 현재의 월에 수입 리스트를 가져온다.
		List<IncomingsVO> incomingsList = incomingsService.incomingsList(ivo);

		// 각 분류별 지출값 계산
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0,
			osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0, osum10 = 0;
		for (int i = 0; i < list.size(); i++) { // 지출 리스트에 각 카테고리에 맞는 각각의 총금액을 구한다.
			System.out.println(list.get(i));
			if (list.get(i).getO_category().equals("식비")) {
				osum1 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("주거/통신")) {
				osum2 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("생활용품")) {
				osum3 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("의복/미용")) {
				osum4 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("건강/문화")) {
				osum5 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("교육/육아")) {
				osum6 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("교통/차량")) {
				osum7 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("경조사/회비")) {
				osum8 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("세금/이자")) {
				osum9 += list.get(i).getOprice();
			} else {
				osum10 += list.get(i).getOprice();
			}
		}
		
		// 각 분류별 지출값을 화면에 보낸다.
		model.addAttribute("osum1", osum1);
		model.addAttribute("osum2", osum2);
		model.addAttribute("osum3", osum3);
		model.addAttribute("osum4", osum4);
		model.addAttribute("osum5", osum5);
		model.addAttribute("osum6", osum6);
		model.addAttribute("osum7", osum7);
		model.addAttribute("osum8", osum8);
		model.addAttribute("osum9", osum9);
		model.addAttribute("osum10", osum10);
		
		// 각 분류별 수입값 계산
		int isum1 = 0, isum2 = 0, isum3 = 0, isum4 = 0;
		for (int i = 0; i < incomingsList.size(); i++) { // 수입 리스트에 각 카테고리에 맞는 각각의 총금액을 구한다.
			if (incomingsList.get(i).getI_category().equals("주수입")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				isum3 += incomingsList.get(i).getIprice();
			} else {
				isum4 += incomingsList.get(i).getIprice();
			}
		}
		// 각 분류별 수입값을 화면에 보낸다.
		model.addAttribute("isum1", isum1);
		model.addAttribute("isum2", isum2);
		model.addAttribute("isum3", isum3);
		model.addAttribute("isum4", isum4);
		
		// 현재 달부터 1년 전까지 각각의 지출 총금액을 담을 리스트
		List<Integer> totalPrice = new ArrayList<Integer>();
		List<OutgoingsVO> outgoings; // 임시로 vo객체를 저장할 리스트 
		for (int i = 0; i < 12; i++) {
			int tmp = 0; // 총 금액을 담아둘 임시 변수
			if (i == 0) { // 현재 월의 총 지출 금액을 구한다.
				for (int j = 0; j < list.size(); j++) {
					tmp += list.get(j).getOprice();
				}
				totalPrice.add(tmp); // 현재 월의 총 지출 금액을 리스트에 저장한다.
			} else { // 현재 월에서 -1월부터 -11월까지 구한다.
				cnt -= 1;
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.MONTH, cnt + 1); // 현재 월에서 -1월 부터 -11월까지 구할 캘린더 클래스

				Date date_price = new Date(cal3.getTimeInMillis());
				vo.setId(member.getId());
				vo.setO_date(date_price);
				// vo에 아이디와 date를 입력해서 -1월부터 -11월까지 각각의 vo리스트를 구한다.
				outgoings = outgoingsService.prevMonth(vo);
				for (int k = 0; k < outgoings.size(); k++) {
					tmp += outgoings.get(k).getOprice();
				}
				totalPrice.add(tmp); // -1월부터 순차적으로 -11월까지 리스트에 저장한다.
			}

		}
		// 위에서 구한 12개월의 총 지출 금액을 화면에 보낸다.
		model.addAttribute("totalPrice", totalPrice);

		// 현재 달부터 1년 전까지 각각의 수입 총금액을 담을 리스트
		List<Integer> ototalPrice = new ArrayList<Integer>();
		List<IncomingsVO> incomings; //임시로 vo객체를 저장할 리스트 
		IncomingsVO invo = new IncomingsVO(); // 리스트 출력을 위해 vo객체 생성
		for (int i = 0; i < 12; i++) {
			int tmp = 0; // 총 금액을 담아둘 임시 변수
			if (i == 0) { // 현재 월의 총 수입 금액을 구한다.
				for (int j = 0; j < incomingsList.size(); j++) {
					tmp += incomingsList.get(j).getIprice();
				}
				ototalPrice.add(tmp); // 현재 월의 총 수입 금액을 리스트에 저장한다.
			} else { // 현재 월에서 -1월부터 -11월까지 구한다.
				cnt_2 -= 1;
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.MONTH, cnt_2 + 1); // 현재 월에서 -1월 부터 -11월까지 구할 캘린더 클래스

				Date date_price = new Date(cal3.getTimeInMillis());
				invo.setId(member.getId());
				invo.setI_date(date_price);
				// vo에 객체에 아이디와 날짜를 저장후 -1월부터 -11월까지의 총 수입 금액 리스트를 구한다.
				incomings = incomingsService.incomingsPrevMonth(invo);
				for (int k = 0; k < incomings.size(); k++) {
					tmp += incomings.get(k).getIprice();
				}
				ototalPrice.add(tmp); // -1월부터 순차적으로 -11월까지 리스트에 저장한다.
			}

		}
		// 위에서 구한 12개월의 총 지출 금액을 화면에 보낸다.
		model.addAttribute("ototalPrice", ototalPrice);
		
		OutgoingsVO newvo = new OutgoingsVO(); // 지금까지의 총 지출 금액을 구하기 위한 vo객체
		newvo.setO_date(d);
		// 지출 내역이 있는 가장 옛날부터 현재까지의 총 지출 금액을 구하기 위한 메소드
		int oto = outgoingsService.totalOutgoingsPrice(newvo);
		// 수입 내역이 있는 가장 옛날부터 현재까지의 총 수입 금액을 구하기 위한 메소드
		int ito = incomingsService.totalIncomingsPrice(ivo);
		
		model.addAttribute("oto", oto);
		model.addAttribute("ito", ito);
		
		return "reference/referenceMain";
	}

	@RequestMapping("/refer_prev_month") // 현재 -1월을 구하는 맵핑
	public String referPrevMonth(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt_3 -= 1;
		cnt_6 -= 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		Calendar re_cal_out = Calendar.getInstance();
		re_cal_out.add(Calendar.MONTH, cnt_3+1);
		
		Date re_date = new Date(re_cal_out.getTimeInMillis());
		OutgoingsVO vo = new OutgoingsVO();
		vo.setId(member.getId());
		vo.setO_date(re_date);
		
		IncomingsVO ivo = new IncomingsVO();
		ivo.setI_date(re_date);
		
		List<OutgoingsVO> outgoingsList = outgoingsService.prevMonth(vo);
		// 각 분류별 지출값 계산
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0, osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0,
				osum10 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			System.out.println(outgoingsList.get(i));
			if (outgoingsList.get(i).getO_category().equals("식비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("주거/통신")) {
				osum2 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("생활용품")) {
				osum3 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("의복/미용")) {
				osum4 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("건강/문화")) {
				osum5 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("교육/육아")) {
				osum6 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("교통/차량")) {
				osum7 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				osum8 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("세금/이자")) {
				osum9 += outgoingsList.get(i).getOprice();
			} else {
				osum10 += outgoingsList.get(i).getOprice();
			}
		}
		
		
		// 각 분류별 지출값
		model.addAttribute("osum1", osum1);
		model.addAttribute("osum2", osum2);
		model.addAttribute("osum3", osum3);
		model.addAttribute("osum4", osum4);
		model.addAttribute("osum5", osum5);
		model.addAttribute("osum6", osum6);
		model.addAttribute("osum7", osum7);
		model.addAttribute("osum8", osum8);
		model.addAttribute("osum9", osum9);
		model.addAttribute("osum10", osum10);
		
		IncomingsVO incomings = new IncomingsVO();
		incomings.setI_date(re_date);
		incomings.setId(member.getId());
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsPrevMonth(incomings);
		
		int isum1 = 0, isum2 = 0, isum3 = 0, isum4 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {
			if (incomingsList.get(i).getI_category().equals("주수입")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				isum3 += incomingsList.get(i).getIprice();
			} else {
				isum4 += incomingsList.get(i).getIprice();
			}
		}

		model.addAttribute("isum1", isum1);
		model.addAttribute("isum2", isum2);
		model.addAttribute("isum3", isum3);
		model.addAttribute("isum4", isum4);
		
		OutgoingsVO re_to_vo = new OutgoingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();
		Calendar o_total = Calendar.getInstance();
		o_total.add(Calendar.MONTH, cnt_3+1);
		Date o_total_date = new Date(o_total.getTimeInMillis());
		re_to_vo.setO_date(o_total_date);
		re_to_vo.setId(member.getId());
		
		List<OutgoingsVO> re_outgoingsList = outgoingsService.prevMonth(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_outgoingsList.size(); j++) {
					tmp += re_outgoingsList.get(j).getOprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+1+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				OutgoingsVO re_outgoings = new OutgoingsVO();
				re_outgoings.setO_date(re_in_date);
				re_outgoings.setId(member.getId());
				List<OutgoingsVO> for_outgoingsList = outgoingsService.prevMonth(re_outgoings);
				
				for (int k = 0; k < for_outgoingsList.size(); k++) {
					tmp += for_outgoingsList.get(k).getOprice();
				}
				totalPrice.add(tmp);
			}

		}
		System.out.println(outgoingsList);
		model.addAttribute("totalPrice", totalPrice);
		
		IncomingsVO re_to_in = new IncomingsVO();
		
		List<Integer> ototalPrice = new ArrayList<Integer>();
		Calendar i_total = Calendar.getInstance();
		i_total.add(Calendar.MONTH, cnt_3+1);
		Date i_total_date = new Date(i_total.getTimeInMillis());
		re_to_in.setI_date(i_total_date);
		re_to_in.setId(member.getId());
		
		List<IncomingsVO> re_incomingsList = incomingsService.incomingsPrevMonth(re_to_in);
		
		int i_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_incomingsList.size(); j++) {
					tmp += re_incomingsList.get(j).getIprice();
				}
				ototalPrice.add(tmp);
			} else {
				
				i_else -= 1;
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+1+i_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				IncomingsVO re_incomings = new IncomingsVO();
				re_incomings.setI_date(re_in_date);
				re_incomings.setId(member.getId());
				
				List<IncomingsVO> for_incomingsList = incomingsService.incomingsPrevMonth(re_incomings);
				
				for (int k = 0; k < for_incomingsList.size(); k++) {

					tmp += for_incomingsList.get(k).getIprice();
				}
				ototalPrice.add(tmp);
			}

		}
		
		model.addAttribute("ototalPrice", ototalPrice);
		
		cnt_5 -= 1;
		Calendar topriceCal = Calendar.getInstance();
		topriceCal.add(Calendar.MONTH, cnt_5);
		Date topridate = new Date(topriceCal.getTimeInMillis());
		
		OutgoingsVO newvo = new OutgoingsVO();
		newvo.setO_date(topridate);
		ivo.setI_date(topridate);
		
		int oto = outgoingsService.totalOutgoingsPrice(newvo);
		int ito = incomingsService.totalIncomingsPrice(ivo);
		
		model.addAttribute("oto", oto);
		model.addAttribute("ito", ito);
		
		return "reference/referenceMain";
	}
	
	
	@RequestMapping("/refer_next_month")
	public String referNextMonth(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		cnt_3 += 1;
		cnt_6 += 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		Calendar re_cal_out = Calendar.getInstance();
		re_cal_out.add(Calendar.MONTH, cnt_3-1);
		
		Date re_date = new Date(re_cal_out.getTimeInMillis());
		OutgoingsVO vo = new OutgoingsVO();
		vo.setId(member.getId());
		vo.setO_date(re_date);
		
		IncomingsVO ivo = new IncomingsVO();
		ivo.setI_date(re_date);
		
		List<OutgoingsVO> outgoingsList = outgoingsService.nextMonth(vo);
		// 각 분류별 지출값 계산
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0, osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0,
				osum10 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			System.out.println(outgoingsList.get(i));
			if (outgoingsList.get(i).getO_category().equals("식비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("주거/통신")) {
				osum2 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("생활용품")) {
				osum3 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("의복/미용")) {
				osum4 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("건강/문화")) {
				osum5 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("교육/육아")) {
				osum6 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("교통/차량")) {
				osum7 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				osum8 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("세금/이자")) {
				osum9 += outgoingsList.get(i).getOprice();
			} else {
				osum10 += outgoingsList.get(i).getOprice();
			}
		}
		
		
		// 각 분류별 지출값
		model.addAttribute("osum1", osum1);
		model.addAttribute("osum2", osum2);
		model.addAttribute("osum3", osum3);
		model.addAttribute("osum4", osum4);
		model.addAttribute("osum5", osum5);
		model.addAttribute("osum6", osum6);
		model.addAttribute("osum7", osum7);
		model.addAttribute("osum8", osum8);
		model.addAttribute("osum9", osum9);
		model.addAttribute("osum10", osum10);
		
		IncomingsVO incomings = new IncomingsVO();
		incomings.setI_date(re_date);
		incomings.setId(member.getId());
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsNextMonth(incomings);
		
		int isum1 = 0, isum2 = 0, isum3 = 0, isum4 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {
			if (incomingsList.get(i).getI_category().equals("주수입")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				isum3 += incomingsList.get(i).getIprice();
			} else {
				isum4 += incomingsList.get(i).getIprice();
			}
		}

		model.addAttribute("isum1", isum1);
		model.addAttribute("isum2", isum2);
		model.addAttribute("isum3", isum3);
		model.addAttribute("isum4", isum4);
		
		OutgoingsVO re_to_vo = new OutgoingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();
		Calendar o_total = Calendar.getInstance();
		o_total.add(Calendar.MONTH, cnt_3-1);
		Date o_total_date = new Date(o_total.getTimeInMillis());
		
		re_to_vo.setO_date(o_total_date);
		re_to_vo.setId(member.getId());
		
		List<OutgoingsVO> re_outgoingsList = outgoingsService.nextMonth(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_outgoingsList.size(); j++) {
					tmp += re_outgoingsList.get(j).getOprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else-1);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				OutgoingsVO re_outgoings = new OutgoingsVO();
				re_outgoings.setO_date(re_in_date);
				re_outgoings.setId(member.getId());
				
				List<OutgoingsVO> for_outgoingsList = outgoingsService.nextMonth(re_outgoings);
				
				for (int k = 0; k < for_outgoingsList.size(); k++) {
					tmp += for_outgoingsList.get(k).getOprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		IncomingsVO re_to_in = new IncomingsVO();
		
		List<Integer> ototalPrice = new ArrayList<Integer>();
		Calendar i_total = Calendar.getInstance();
		i_total.add(Calendar.MONTH, cnt_3-1);
		Date i_total_date = new Date(i_total.getTimeInMillis());
		
		re_to_in.setI_date(i_total_date);
		re_to_in.setId(member.getId());
		
		List<IncomingsVO> re_incomingsList = incomingsService.incomingsNextMonth(re_to_in);
		
		int i_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_incomingsList.size(); j++) {
					tmp += re_incomingsList.get(j).getIprice();
				}
				ototalPrice.add(tmp);
			} else {
				
				i_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+i_else-1);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				IncomingsVO re_incomings = new IncomingsVO();
				re_incomings.setI_date(re_in_date);
				re_incomings.setId(member.getId());
				
				List<IncomingsVO> for_incomingsList = incomingsService.incomingsNextMonth(re_incomings);
				
				for (int k = 0; k < for_incomingsList.size(); k++) {

					tmp += for_incomingsList.get(k).getIprice();
				}
				ototalPrice.add(tmp);
			}

		}
		
		model.addAttribute("ototalPrice", ototalPrice);
		
		cnt_5 += 1;
		Calendar topriceCal = Calendar.getInstance();
		topriceCal.add(Calendar.MONTH, cnt_5);
		Date topridate = new Date(topriceCal.getTimeInMillis());
		
		OutgoingsVO newvo = new OutgoingsVO();
		
		newvo.setO_date(topridate);
		ivo.setI_date(topridate);
		
		int oto = outgoingsService.totalOutgoingsPrice(newvo);
		int ito = incomingsService.totalIncomingsPrice(ivo);
		
		model.addAttribute("oto", oto);
		model.addAttribute("ito", ito);
		
		return "reference/referenceMain";
	}
	
	@RequestMapping("/outgoings_category1")
	public String outgoingsCatrgory1(HttpSession session, Model model, OutgoingsVO oVo,
			@RequestParam(value = "o_date", required = false) Date o_date,
			@RequestParam(value = "o_category", required = false) String o_category) {
		
		session.setAttribute("o_category", o_category);
		
		String o = (String)session.getAttribute("o_category");
		model.addAttribute("o_category", o_category);
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		Date realdate = new Date(Calendar.getInstance().getTimeInMillis());
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		OutgoingsVO vo = new OutgoingsVO();
		vo.setId(member.getId());
		vo.setO_date(oVo.getO_date());
		vo.setO_category(o);
		
		List<OutgoingsVO> outgoingsList = outgoingsService.getOutgoingsCategoryList(vo);
		
		List<OutgoingsVO> out_cate_list = new ArrayList<OutgoingsVO>();
		for(int i=0; i<outgoingsList.size(); i++) {
			if(outgoingsList.get(i).getO_category().equals("식비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			if (outgoingsList.get(i).getO_category().equals("식비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				osum1 += outgoingsList.get(i).getOprice();
			}
		}		
		model.addAttribute("osum1", osum1);
		
		OutgoingsVO re_to_vo = new OutgoingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();
		
		Calendar o_total = Calendar.getInstance();
		o_total.add(Calendar.MONTH, cnt_3);
		Date o_total_date = new Date(o_total.getTimeInMillis());
		
		re_to_vo.setO_date(o_total_date);
		re_to_vo.setId(member.getId());
		re_to_vo.setO_category(o);
		
		List<OutgoingsVO> re_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_outgoingsList.size(); j++) {
					tmp += re_outgoingsList.get(j).getOprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				OutgoingsVO re_outgoings = new OutgoingsVO();
				re_outgoings.setO_date(re_in_date);
				re_outgoings.setId(member.getId());
				re_outgoings.setO_category(o);
				
				List<OutgoingsVO> for_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_outgoings);
				for (int k = 0; k < for_outgoingsList.size(); k++) {
					tmp += for_outgoingsList.get(k).getOprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);	
		
		return "reference/referenceOutgoingsCategory1";
	}
	
	@RequestMapping("/outgoings_category1_prev")
	public String outgoingsCategoryPrev(HttpSession session, Model model, OutgoingsVO oVo,
			@RequestParam(value = "o_category", required = false)String o_category) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		cnt_6 -= 1; cnt_3 -= 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		OutgoingsVO vo = new OutgoingsVO();
		vo.setId(member.getId());
		vo.setO_date(realdate);
		vo.setO_category(o_category);
		
		List<OutgoingsVO> outgoingsList = outgoingsService.getOutgoingsCategoryList(vo);
		
		List<OutgoingsVO> out_cate_list = new ArrayList<OutgoingsVO>();
		for(int i=0; i<outgoingsList.size(); i++) {
			if(outgoingsList.get(i).getO_category().equals("식비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			if (outgoingsList.get(i).getO_category().equals("식비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				osum1 += outgoingsList.get(i).getOprice();
			}
		}
		
		model.addAttribute("osum1", osum1);
			
		OutgoingsVO re_to_vo = new OutgoingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();
		
		Calendar o_total = Calendar.getInstance();
		o_total.add(Calendar.MONTH, cnt_3);
		Date o_total_date = new Date(o_total.getTimeInMillis());
		
		re_to_vo.setO_date(o_total_date);
		re_to_vo.setId(member.getId());
		re_to_vo.setO_category(oVo.getO_category());
		
		List<OutgoingsVO> re_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_outgoingsList.size(); j++) {
					tmp += re_outgoingsList.get(j).getOprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				OutgoingsVO re_outgoings = new OutgoingsVO();
				re_outgoings.setO_date(re_in_date);
				re_outgoings.setId(member.getId());
				re_outgoings.setO_category(oVo.getO_category());
				
				List<OutgoingsVO> for_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_outgoings);
				
				for (int k = 0; k < for_outgoingsList.size(); k++) {
					tmp += for_outgoingsList.get(k).getOprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "reference/referenceOutgoingsCategory1";
	}
	
	
	@RequestMapping("/outgoings_category1_next")
	public String outgoingsCategoryNext(HttpSession session, Model model, OutgoingsVO oVo,
			@RequestParam(value = "o_category", required = false)String o_category) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		cnt_6 += 1; cnt_3 += 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
	
		OutgoingsVO vo = new OutgoingsVO();
		vo.setId(member.getId());
		vo.setO_date(realdate);
		vo.setO_category(o_category);
		
		List<OutgoingsVO> outgoingsList = outgoingsService.getOutgoingsCategoryList(vo);
		
		List<OutgoingsVO> out_cate_list = new ArrayList<OutgoingsVO>();
		for(int i=0; i<outgoingsList.size(); i++) {
			if(outgoingsList.get(i).getO_category().equals("식비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {

			if (outgoingsList.get(i).getO_category().equals("식비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("주거/통신")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("생활용품")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("의복/미용")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("건강/문화")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교육/육아")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("교통/차량")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("경조사/회비")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("세금/이자")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("용돈/기타")) {
				osum1 += outgoingsList.get(i).getOprice();
			}
		}
		
		model.addAttribute("osum1", osum1);
			
		OutgoingsVO re_to_vo = new OutgoingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();
		
		Calendar o_total = Calendar.getInstance();
		o_total.add(Calendar.MONTH, cnt_3);
		Date o_total_date = new Date(o_total.getTimeInMillis());
		
		re_to_vo.setO_date(o_total_date);
		re_to_vo.setId(member.getId());
		re_to_vo.setO_category(o_category);
		
		List<OutgoingsVO> re_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_outgoingsList.size(); j++) {
					tmp += re_outgoingsList.get(j).getOprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				OutgoingsVO re_outgoings = new OutgoingsVO();
				re_outgoings.setO_date(re_in_date);
				re_outgoings.setId(member.getId());
				re_outgoings.setO_category(o_category);
				
				List<OutgoingsVO> for_outgoingsList = outgoingsService.getOutgoingsCategoryList(re_outgoings);
				
				for (int k = 0; k < for_outgoingsList.size(); k++) {
					tmp += for_outgoingsList.get(k).getOprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "reference/referenceOutgoingsCategory1";
	}
	
	@RequestMapping("/incomings_category1")
	public String incomingsCategory1(HttpSession session, Model model, IncomingsVO iVo,
			@RequestParam(value = "o_date", required = false) Date i_date,
			@RequestParam(value = "i_category", required = false) String i_category) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		session.setAttribute("i_category", i_category);
		model.addAttribute("i_category", i_category);
		
		String icate = (String)session.getAttribute("i_category");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		IncomingsVO vo = new IncomingsVO();
		vo.setId(member.getId());
		vo.setI_date(iVo.getI_date());
		vo.setI_category(icate);
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsListCategory(vo);
		
		List<IncomingsVO> out_cate_list = new ArrayList<IncomingsVO>();
		for(int i=0; i<incomingsList.size(); i++) {
			if(incomingsList.get(i).getI_category().equals("주수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("부수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("전월이월")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("저축/보험")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {

			if (incomingsList.get(i).getI_category().equals("주수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("저축/보험")) {
				osum1 += incomingsList.get(i).getIprice();
			}
		}
		
		model.addAttribute("osum1", osum1);
		
		
		IncomingsVO re_to_vo = new IncomingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();

		re_to_vo.setI_date(iVo.getI_date());
		re_to_vo.setId(member.getId());
		re_to_vo.setI_category(icate);
		
		List<IncomingsVO> re_incomingsList = incomingsService.incomingsListCategory(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_incomingsList.size(); j++) {
					tmp += re_incomingsList.get(j).getIprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				IncomingsVO re_incomings = new IncomingsVO();
				re_incomings.setI_date(re_in_date);
				re_incomings.setId(member.getId());
				re_incomings.setI_category(icate);
				
				List<IncomingsVO> for_incomingsList = incomingsService.incomingsListCategory(re_incomings);
				
				for (int k = 0; k < for_incomingsList.size(); k++) {
					tmp += for_incomingsList.get(k).getIprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "reference/referenceIncomingsCategory1";
	}
	
	@RequestMapping("/incomings_category1_prev")
	public String incomingsCategoryPrev1(HttpSession session, Model model, 
			@RequestParam(value = "o_date", required = false) Date i_date,
			@RequestParam(value = "i_category", required = false) String i_category) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		cnt_6 -= 1; cnt_3 -= 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		IncomingsVO vo = new IncomingsVO();
		vo.setId(member.getId());
		vo.setI_date(realdate);
		vo.setI_category(i_category);
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsListCategory(vo);
		
		List<IncomingsVO> out_cate_list = new ArrayList<IncomingsVO>();
		for(int i=0; i<incomingsList.size(); i++) {
			if(incomingsList.get(i).getI_category().equals("주수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("부수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("전월이월")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("저축/보험")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {

			if (incomingsList.get(i).getI_category().equals("주수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("저축/보험")) {
				osum1 += incomingsList.get(i).getIprice();
			}
		}
		
		model.addAttribute("osum1", osum1);
		
		
		IncomingsVO re_to_vo = new IncomingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();

		re_to_vo.setI_date(realdate);
		re_to_vo.setId(member.getId());
		re_to_vo.setI_category(i_category);
		
		List<IncomingsVO> re_incomingsList = incomingsService.incomingsListCategory(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_incomingsList.size(); j++) {
					tmp += re_incomingsList.get(j).getIprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				IncomingsVO re_incomings = new IncomingsVO();
				re_incomings.setI_date(re_in_date);
				re_incomings.setId(member.getId());
				re_incomings.setI_category(i_category);
				
				List<IncomingsVO> for_incomingsList = incomingsService.incomingsListCategory(re_incomings);
				
				for (int k = 0; k < for_incomingsList.size(); k++) {
					tmp += for_incomingsList.get(k).getIprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "reference/referenceIncomingsCategory1";
	}
	
	@RequestMapping("/incomings_category1_next")
	public String incomingsCategoryNext1(HttpSession session, Model model, 
			@RequestParam(value = "o_date", required = false) Date i_date,
			@RequestParam(value = "i_category", required = false) String i_category) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(member == null && worker == null) {
			return "member/loginForm";
		}
		
		cnt_6 += 1; cnt_3 += 1;
		
		Calendar date_cal = Calendar.getInstance();
		date_cal.add(Calendar.MONTH, cnt_3);
		Date realdate = new Date(date_cal.getTimeInMillis());
		
		model.addAttribute("realdate", realdate);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				cal.add(Calendar.MONTH, cnt_6);
				years.add(simpledate.format(cal.getTime()));
			} else {
				cal.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal.getTime()));
			}
		}

		model.addAttribute("date", years);
		
		IncomingsVO vo = new IncomingsVO();
		vo.setId(member.getId());
		vo.setI_date(realdate);
		vo.setI_category(i_category);
		
		List<IncomingsVO> incomingsList = incomingsService.incomingsListCategory(vo);
		
		List<IncomingsVO> out_cate_list = new ArrayList<IncomingsVO>();
		for(int i=0; i<incomingsList.size(); i++) {
			if(incomingsList.get(i).getI_category().equals("주수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("부수입")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("전월이월")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("저축/보험")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {
			if (incomingsList.get(i).getI_category().equals("주수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("부수입")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("전월이월")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("저축/보험")) {
				osum1 += incomingsList.get(i).getIprice();
			}
		}
		
		model.addAttribute("osum1", osum1);	
		
		IncomingsVO re_to_vo = new IncomingsVO();
		
		List<Integer> totalPrice = new ArrayList<Integer>();

		re_to_vo.setI_date(realdate);
		re_to_vo.setId(member.getId());
		re_to_vo.setI_category(i_category);
		
		List<IncomingsVO> re_incomingsList = incomingsService.incomingsListCategory(re_to_vo);
		
		int o_else = 0;
		for (int i = 0; i < 12; i++) {
			int tmp = 0;
			if (i == 0) {
				for (int j = 0; j < re_incomingsList.size(); j++) {
					tmp += re_incomingsList.get(j).getIprice();
				}
				totalPrice.add(tmp);
			} else {
				o_else -= 1;
				
				Calendar else_in_cal = Calendar.getInstance();
				else_in_cal.add(Calendar.MONTH, cnt_3+o_else);
				Date re_in_date = new Date(else_in_cal.getTimeInMillis());
				
				IncomingsVO re_incomings = new IncomingsVO();
				re_incomings.setI_date(re_in_date);
				re_incomings.setId(member.getId());
				re_incomings.setI_category(i_category);
				
				List<IncomingsVO> for_incomingsList = incomingsService.incomingsListCategory(re_incomings);
				
				for (int k = 0; k < for_incomingsList.size(); k++) {
					tmp += for_incomingsList.get(k).getIprice();
				}
				totalPrice.add(tmp);
			}

		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "reference/referenceIncomingsCategory1";
	}
	
	
}
