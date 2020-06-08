package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.biz.member.WorkerVO;
import com.green.biz.noticeBoard.NoticeBoardService;
import com.green.biz.noticeBoard.NoticeBoardVO;
import com.green.biz.util.PageMaker;
import com.green.biz.util.SearchCriteria;

@Controller
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@RequestMapping("/noticeBoard") // �������� ����Ʈ�� ȭ�鿡 ���
	public String noticeBoardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		// ����Ʈ ����¡ ���� ó��
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(noticeBoardService.noticeBoardCount(scri));
		
		List<NoticeBoardVO> noticeBoardList = noticeBoardService.noticeBoardList(scri);
		
		model.addAttribute("noticeBoardList", noticeBoardList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "noticeBoard/noticeBoardList";
	}
	
	@RequestMapping("/noticeBoard_detail") // �������� ��ȭ�� ���
	public String noticeBoardDetail(Model model, NoticeBoardVO vo) {
		// �� ȭ�� ��ȸ�� ��ȸ���� +1�� ����
		noticeBoardService.noticeBoardCnt(vo.getNseq());
		NoticeBoardVO noticeBoard =  noticeBoardService.getNoticeBoard(vo);
		
		model.addAttribute("noticeBoard", noticeBoard);
		
		return "noticeBoard/noticeBoardDetail";
	}
	
	@RequestMapping("/noticeBoard_write_form") // �������� �۾��� ȭ�� ���
	public String noticeBoardWriteForm(HttpSession session) {
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) { // ������ �α����� �ȵǾ� �־�����
			return "redirect:main";
		}
		
		return "noticeBoard/noticeBoardWriteForm";
	}
	
	@RequestMapping("/notice_board_write") // �������� �� ��� ó��
	public String noticeBoardWrite(NoticeBoardVO vo) {
		
		noticeBoardService.insertNoticeBoard(vo);
		
		return "redirect:noticeBoard";
	}
	
	@RequestMapping("/notice_board_update_form") // �������� �� ���� ȭ�� ���
	public String noticeBoardUpdateForm(NoticeBoardVO vo, Model model, HttpSession session) {
		WorkerVO worker = (WorkerVO)session.getAttribute("adminUser");
		
		if(worker == null) { // ������ �α����� �ȵǾ� �־�����
			return "redirect:main";
		}
		// �������� �� ���� ó��
		NoticeBoardVO noticeBoard = noticeBoardService.getNoticeBoard(vo);
		
		model.addAttribute("noticeBoard", noticeBoard);
		
		return "noticeBoard/noticeBoardUpdateForm";
	}
	
	@RequestMapping("/notice_board_update") // �������� �� ���� ó��
	public String noticeBoardUpdate(NoticeBoardVO vo) {
		
		noticeBoardService.updateNoticeBoard(vo);
		
		return "redirect:noticeBoard";
	}
	
	@RequestMapping("/notice_board_delete") // �������� �� ���� ó��
	public String noticeBoardDelete(NoticeBoardVO vo) {
		
		noticeBoardService.deleteNoticeBoard(vo.getNseq());
		
		return "redirect:noticeBoard";
	}
	
}
