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

	static int cnt = 0; // �� ���� �ݾ� ���ϱ� ���� ����
	static int cnt_2 = 0; // �� ���� �ݾ� ���ϱ� ���� ����
	static int cnt_3 = 0; // �� �� �� ���� �� �̵��� ���� ����
	static int cnt_5 = 0; // �� ���� �� ���� �ݾ� ���ϱ� ���� ����
	static int cnt_6 = 0; // ��Ʈ�� 12���� ���ϱ� ���� ����

	@RequestMapping("/reference") // ���� ���� ��ȸ ������
	public String referenceMain(HttpSession session, Model model) {

		MemberVO member = (MemberVO)session.getAttribute("loginUser"); // ����� ������ ���ǿ� ����
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser"); // ������ ������ ���ǿ� ����
		
		if(member == null && worker == null) { // ����� �α��� �Ǵ� ������ �α����� �ȵ� ���¸� �α��� â���� �̵�
			return "member/loginForm";
		}
		cnt = 0; cnt_2 = 0; cnt_3 = 0; cnt_5 = 0; cnt_6 = 0;
		
		Date realdate = new Date(Calendar.getInstance().getTimeInMillis()); 
		model.addAttribute("realdate", realdate); // �������� ���� ��¥�� �Ѱ���

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal.add(Calendar.MONTH, 0);
		cal2.add(Calendar.MONTH, 0);
		Date d = new Date(cal.getTimeInMillis()); // ���� �ð��� ��¥�� ������ ����
		// ��ȸ ����ȭ�鿡 yyyy.MM �������� ��¥ ����ϱ� ���� SimpleDateFormat
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy.MM", Locale.KOREA);

		List<String> years = new ArrayList<String>(); // ����Ʈ x���� 12���� ���� ��� ���� List
		for (int i = 0; i < 12; i++) {
			if (i == 0) { // ���� ���� �ش��ϴ� ��¥�� ����Ʈ�� �߰��Ѵ�.
				cal2.add(Calendar.MONTH, 0);
				years.add(simpledate.format(cal2.getTime()));
			} else { // ���� ��-1 ������ �ؼ� -11������ ����Ʈ�� �߰��Ѵ�.
				cal2.add(Calendar.MONTH, -1);
				years.add(simpledate.format(cal2.getTime()));
			}
		}
		
		model.addAttribute("date", years); // 12������ ����Ʈ�� ȭ�鿡 �Ѱ���
			
		OutgoingsVO vo = new OutgoingsVO(); // �ش� ���� ������ ���ϱ� ���� vo��ü
		IncomingsVO ivo = new IncomingsVO(); // �ش� ���� ������ ���ϱ� ���� vo��ü
		
		vo.setO_date(d); // ���� vo�� ���� ��¥�� �ִ´�.
		vo.setId(member.getId()); // ���� vo�� ���̵� �ִ´�.
		
		ivo.setI_date(d); // ���� vo�� ���� ��¥�� �ִ´�.
		ivo.setId(member.getId()); // ���� vo�� ���̵� �ִ´�.
		
		// �Էµ� ���̵�� ��¥�� ������ ���� ���� ����Ʈ�� �����´�.
		List<OutgoingsVO> list = outgoingsService.getOutgoingsList(vo);
		// �Էµ� ���̵�� ��¥�� ������ ���� ���� ����Ʈ�� �����´�.
		List<IncomingsVO> incomingsList = incomingsService.incomingsList(ivo);

		// �� �з��� ���Ⱚ ���
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0,
			osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0, osum10 = 0;
		for (int i = 0; i < list.size(); i++) { // ���� ����Ʈ�� �� ī�װ��� �´� ������ �ѱݾ��� ���Ѵ�.
			System.out.println(list.get(i));
			if (list.get(i).getO_category().equals("�ĺ�")) {
				osum1 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("�ְ�/���")) {
				osum2 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum3 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum4 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum5 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("����/����")) {
				osum6 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("����/����")) {
				osum7 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("������/ȸ��")) {
				osum8 += list.get(i).getOprice();
			} else if (list.get(i).getO_category().equals("����/����")) {
				osum9 += list.get(i).getOprice();
			} else {
				osum10 += list.get(i).getOprice();
			}
		}
		
		// �� �з��� ���Ⱚ�� ȭ�鿡 ������.
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
		
		// �� �з��� ���԰� ���
		int isum1 = 0, isum2 = 0, isum3 = 0, isum4 = 0;
		for (int i = 0; i < incomingsList.size(); i++) { // ���� ����Ʈ�� �� ī�װ��� �´� ������ �ѱݾ��� ���Ѵ�.
			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
				isum3 += incomingsList.get(i).getIprice();
			} else {
				isum4 += incomingsList.get(i).getIprice();
			}
		}
		// �� �з��� ���԰��� ȭ�鿡 ������.
		model.addAttribute("isum1", isum1);
		model.addAttribute("isum2", isum2);
		model.addAttribute("isum3", isum3);
		model.addAttribute("isum4", isum4);
		
		// ���� �޺��� 1�� ������ ������ ���� �ѱݾ��� ���� ����Ʈ
		List<Integer> totalPrice = new ArrayList<Integer>();
		List<OutgoingsVO> outgoings; // �ӽ÷� vo��ü�� ������ ����Ʈ 
		for (int i = 0; i < 12; i++) {
			int tmp = 0; // �� �ݾ��� ��Ƶ� �ӽ� ����
			if (i == 0) { // ���� ���� �� ���� �ݾ��� ���Ѵ�.
				for (int j = 0; j < list.size(); j++) {
					tmp += list.get(j).getOprice();
				}
				totalPrice.add(tmp); // ���� ���� �� ���� �ݾ��� ����Ʈ�� �����Ѵ�.
			} else { // ���� ������ -1������ -11������ ���Ѵ�.
				cnt -= 1;
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.MONTH, cnt + 1); // ���� ������ -1�� ���� -11������ ���� Ķ���� Ŭ����

				Date date_price = new Date(cal3.getTimeInMillis());
				vo.setId(member.getId());
				vo.setO_date(date_price);
				// vo�� ���̵�� date�� �Է��ؼ� -1������ -11������ ������ vo����Ʈ�� ���Ѵ�.
				outgoings = outgoingsService.prevMonth(vo);
				for (int k = 0; k < outgoings.size(); k++) {
					tmp += outgoings.get(k).getOprice();
				}
				totalPrice.add(tmp); // -1������ ���������� -11������ ����Ʈ�� �����Ѵ�.
			}

		}
		// ������ ���� 12������ �� ���� �ݾ��� ȭ�鿡 ������.
		model.addAttribute("totalPrice", totalPrice);

		// ���� �޺��� 1�� ������ ������ ���� �ѱݾ��� ���� ����Ʈ
		List<Integer> ototalPrice = new ArrayList<Integer>();
		List<IncomingsVO> incomings; //�ӽ÷� vo��ü�� ������ ����Ʈ 
		IncomingsVO invo = new IncomingsVO(); // ����Ʈ ����� ���� vo��ü ����
		for (int i = 0; i < 12; i++) {
			int tmp = 0; // �� �ݾ��� ��Ƶ� �ӽ� ����
			if (i == 0) { // ���� ���� �� ���� �ݾ��� ���Ѵ�.
				for (int j = 0; j < incomingsList.size(); j++) {
					tmp += incomingsList.get(j).getIprice();
				}
				ototalPrice.add(tmp); // ���� ���� �� ���� �ݾ��� ����Ʈ�� �����Ѵ�.
			} else { // ���� ������ -1������ -11������ ���Ѵ�.
				cnt_2 -= 1;
				Calendar cal3 = Calendar.getInstance();
				cal3.add(Calendar.MONTH, cnt_2 + 1); // ���� ������ -1�� ���� -11������ ���� Ķ���� Ŭ����

				Date date_price = new Date(cal3.getTimeInMillis());
				invo.setId(member.getId());
				invo.setI_date(date_price);
				// vo�� ��ü�� ���̵�� ��¥�� ������ -1������ -11�������� �� ���� �ݾ� ����Ʈ�� ���Ѵ�.
				incomings = incomingsService.incomingsPrevMonth(invo);
				for (int k = 0; k < incomings.size(); k++) {
					tmp += incomings.get(k).getIprice();
				}
				ototalPrice.add(tmp); // -1������ ���������� -11������ ����Ʈ�� �����Ѵ�.
			}

		}
		// ������ ���� 12������ �� ���� �ݾ��� ȭ�鿡 ������.
		model.addAttribute("ototalPrice", ototalPrice);
		
		OutgoingsVO newvo = new OutgoingsVO(); // ���ݱ����� �� ���� �ݾ��� ���ϱ� ���� vo��ü
		newvo.setO_date(d);
		// ���� ������ �ִ� ���� �������� ��������� �� ���� �ݾ��� ���ϱ� ���� �޼ҵ�
		int oto = outgoingsService.totalOutgoingsPrice(newvo);
		// ���� ������ �ִ� ���� �������� ��������� �� ���� �ݾ��� ���ϱ� ���� �޼ҵ�
		int ito = incomingsService.totalIncomingsPrice(ivo);
		
		model.addAttribute("oto", oto);
		model.addAttribute("ito", ito);
		
		return "reference/referenceMain";
	}

	@RequestMapping("/refer_prev_month") // ���� -1���� ���ϴ� ����
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
		// �� �з��� ���Ⱚ ���
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0, osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0,
				osum10 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			System.out.println(outgoingsList.get(i));
			if (outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				osum2 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum3 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum4 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum5 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum6 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum7 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				osum8 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum9 += outgoingsList.get(i).getOprice();
			} else {
				osum10 += outgoingsList.get(i).getOprice();
			}
		}
		
		
		// �� �з��� ���Ⱚ
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
			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
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
		// �� �з��� ���Ⱚ ���
		int osum1 = 0, osum2 = 0, osum3 = 0, osum4 = 0, osum5 = 0, osum6 = 0, osum7 = 0, osum8 = 0, osum9 = 0,
				osum10 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			System.out.println(outgoingsList.get(i));
			if (outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				osum2 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum3 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum4 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum5 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum6 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum7 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				osum8 += outgoingsList.get(i).getOprice();
			} else if (outgoingsList.get(i).getO_category().equals("����/����")) {
				osum9 += outgoingsList.get(i).getOprice();
			} else {
				osum10 += outgoingsList.get(i).getOprice();
			}
		}
		
		
		// �� �з��� ���Ⱚ
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
			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				isum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				isum2 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
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
			if(outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			if (outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
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
			if(outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {
			if (outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
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
			if(outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				out_cate_list.add(outgoingsList.get(i));
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
				out_cate_list.add(outgoingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < outgoingsList.size(); i++) {

			if (outgoingsList.get(i).getO_category().equals("�ĺ�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ְ�/���")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("��Ȱ��ǰ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�Ǻ�/�̿�")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�ǰ�/��ȭ")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("������/ȸ��")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("����/����")) {
				osum1 += outgoingsList.get(i).getOprice();
			} else if(outgoingsList.get(i).getO_category().equals("�뵷/��Ÿ")) {
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
			if(incomingsList.get(i).getI_category().equals("�ּ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�μ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�����̿�")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("����/����")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {

			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("����/����")) {
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
			if(incomingsList.get(i).getI_category().equals("�ּ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�μ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�����̿�")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("����/����")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {

			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("����/����")) {
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
			if(incomingsList.get(i).getI_category().equals("�ּ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�μ���")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("�����̿�")) {
				out_cate_list.add(incomingsList.get(i));
			} else if(incomingsList.get(i).getI_category().equals("����/����")) {
				out_cate_list.add(incomingsList.get(i));
			}
		}
		
		model.addAttribute("out_cate_list", out_cate_list);
		
		int osum1 = 0;
		for (int i = 0; i < incomingsList.size(); i++) {
			if (incomingsList.get(i).getI_category().equals("�ּ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�μ���")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("�����̿�")) {
				osum1 += incomingsList.get(i).getIprice();
			} else if (incomingsList.get(i).getI_category().equals("����/����")) {
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
