package com.green.biz.noticeBoard;

import java.sql.Date;

public class NoticeBoardVO {
	private int nseq;
	private String ntitle;
	private String n_content;
	private String id;
	private Date regdate;
	private int cnt;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getNseq() {
		return nseq;
	}
	public void setNseq(int nseq) {
		this.nseq = nseq;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
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
		return "NoticeBoardVO [nseq=" + nseq + ", ntitle=" + ntitle + ", n_content=" + n_content + ", id=" + id
				+ ", regdate=" + regdate + ", cnt=" + cnt + "]";
	}
	
}
