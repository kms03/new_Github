package com.green.biz.reply;

import java.sql.Date;

public class ReplyVO {
	private int bseq;
	private int rno;
	private String re_content;
	private String id;
	private Date regdate;
	
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ReplyVO [bseq=" + bseq + ", rno=" + rno + ", re_content=" + re_content + ", id=" + id + ", regdate=" + regdate
				+ "]";
	}
	
}
