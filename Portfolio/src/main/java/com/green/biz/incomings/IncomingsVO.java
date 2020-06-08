package com.green.biz.incomings;

import java.sql.Date;

public class IncomingsVO {
	private int iseq;
	private int iprice;
	private String i_use;
	private String i_category;
	private String i_memo;
	private Date i_date;
	private String id;
	private String key;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public int getIprice() {
		return iprice;
	}
	public void setIprice(int iprice) {
		this.iprice = iprice;
	}
	public String getI_use() {
		return i_use;
	}
	public void setI_use(String i_use) {
		this.i_use = i_use;
	}
	public String getI_category() {
		return i_category;
	}
	public void setI_category(String i_category) {
		this.i_category = i_category;
	}
	public String getI_memo() {
		return i_memo;
	}
	public void setI_memo(String i_memo) {
		this.i_memo = i_memo;
	}
	public Date getI_date() {
		return i_date;
	}
	public void setI_date(Date i_date) {
		this.i_date = i_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "IncomingsVO [iseq=" + iseq + ", iprice=" + iprice + ", i_use=" + i_use + ", i_category=" + i_category
				+ ", i_memo=" + i_memo + ", i_date=" + i_date + ", id=" + id + ", key=" + key + ", keyword=" + keyword
				+ ", searchType=" + searchType + "]";
	}
	
}
