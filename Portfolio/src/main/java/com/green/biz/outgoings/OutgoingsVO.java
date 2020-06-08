package com.green.biz.outgoings;

import java.sql.Date;

import com.green.biz.util.SearchCriteria;

public class OutgoingsVO extends SearchCriteria {
	private int oseq;
	private int oprice;
	private String o_use;
	private String o_where;
	private int cash_money;
	private String o_category;
	private String o_memo;
	private Date o_date;
	private String id;
	private int cnt;
	private String keyword;
	private String searchType;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getOseq() {
		return oseq;
	}
	public void setOseq(int oseq) {
		this.oseq = oseq;
	}
	public int getOprice() {
		return oprice;
	}
	public void setOprice(int oprice) {
		this.oprice = oprice;
	}
	public String getO_use() {
		return o_use;
	}
	public void setO_use(String o_use) {
		this.o_use = o_use;
	}
	public String getO_where() {
		return o_where;
	}
	public void setO_where(String o_where) {
		this.o_where = o_where;
	}
	public int getCash_money() {
		return cash_money;
	}
	public void setCash_money(int cash_money) {
		this.cash_money = cash_money;
	}
	public String getO_category() {
		return o_category;
	}
	public void setO_category(String o_category) {
		this.o_category = o_category;
	}
	public String getO_memo() {
		return o_memo;
	}
	public void setO_memo(String o_memo) {
		this.o_memo = o_memo;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "OutgoingsVO [oseq=" + oseq + ", oprice=" + oprice + ", o_use=" + o_use + ", o_where=" + o_where
				+ ", cash_money=" + cash_money + ", o_category=" + o_category + ", o_memo=" + o_memo + ", o_date="
				+ o_date + ", id=" + id + ", cnt=" + cnt + ", keyword=" + keyword + ", searchType=" + searchType + "]";
	}
	
	
}
