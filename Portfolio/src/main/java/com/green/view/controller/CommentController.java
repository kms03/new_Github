package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.green.biz.member.MemberVO;
import com.green.biz.reply.ReplyService;
import com.green.biz.reply.ReplyVO;

@RestController
public class CommentController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "/save_comment", method = RequestMethod.POST)
	@ResponseBody
	public String saveComment(ReplyVO vo, HttpSession session,
			@RequestParam(value = "re_content") String re_content,
			@RequestParam(value = "bseq")int bseq) {
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		if(member == null) {
			return ("fail");
		} else {
			String userId = member.getId();
			vo.setId(userId);
			vo.setBseq(bseq);
			vo.setRe_content(re_content);
			System.out.println(vo);
			replyService.insertReply(vo);
			System.out.println(vo);
			return "success";
		}
		
	}
	/*
	@RequestMapping("comment_list")
	public List<ReplyVO> getCommentList(ReplyVO vo) {
		
		int bseq = vo.getBseq();
		
		
		
		return commentList;
		
	}*/
	
}
