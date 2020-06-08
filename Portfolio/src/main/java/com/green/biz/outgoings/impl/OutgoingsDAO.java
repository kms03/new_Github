package com.green.biz.outgoings.impl;

import java.sql.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.outgoings.OutgoingsVO;
import com.green.biz.util.Criteria;
import com.green.biz.util.SearchCriteria;

@Repository
public class OutgoingsDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<OutgoingsVO> getOutgoingsList(OutgoingsVO vo) {
		System.out.println("===> mybatis로 getOutgoingsList() 기능 실행");
		
		return mybatis.selectList("OutgoingsDAO.getOutgoingsList", vo);
	}
	
	public List<OutgoingsVO> prevMonth(OutgoingsVO vo) {
		System.out.println("===> mybatis로 prevMonth() 기능 실행");
		
		return mybatis.selectList("OutgoingsDAO.prevMonth", vo);
	}
	
	public OutgoingsVO getOutgoings(OutgoingsVO vo) {
		
		return mybatis.selectOne("OutgoingsDAO.getOutgoings", vo);
		
	}
	
	public List<OutgoingsVO> nextMonth(OutgoingsVO vo) {
		System.out.println("===> mybatis로 prevMonth() 기능 실행");
		
		return mybatis.selectList("OutgoingsDAO.nextMonth", vo);
	}
	
	public int listCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("OutgoingsDAO.listCount", scri);
		
	}
	
	public void insertOutgoings(OutgoingsVO vo) {
		System.out.println("===> mybatis로 insertOutgoings() 기능 실행");
		
		mybatis.insert("OutgoingsDAO.insertOutgoings", vo);
	}
	
	public List<OutgoingsVO> getOutgoingsCategoryList(OutgoingsVO vo) {
		
		return mybatis.selectList("OutgoingsDAO.getOutgoingsCategoryList", vo);
	}
	
	public void outgoingsDelete(OutgoingsVO vo) {
		
		mybatis.delete("OutgoingsDAO.outgoingsDelete", vo);
	}
	
	public List<OutgoingsVO> scriOutgoingsList(SearchCriteria scri) throws Exception {
		
		return mybatis.selectList("OutgoingsDAO.scriGetOutgoingsList", scri);
		
	}
	
	public int outListCount(SearchCriteria scri) throws Exception {
		
		return mybatis.selectOne("OutgoingsDAO.listCount", scri);
	}
	
	public void outgoingsDeleteById(String id) {
		
		mybatis.delete("OutgoingsDAO.outgoingsDeleteById", id);
	}
	
	public int totalOutgoingsPrice(OutgoingsVO vo) {
		
		return mybatis.selectOne("OutgoingsDAO.totalOutgoingsPrice", vo);
	}
	
}
