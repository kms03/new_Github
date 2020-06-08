package com.green.biz.incomings.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.incomings.IncomingsVO;

@Repository
public class IncomingsDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<IncomingsVO> incomingsList(IncomingsVO vo) {
		System.out.println("===> mybatis�� incomingsList() ��� ����");
		
		return mybatis.selectList("IncomingsDAO.incomingsList", vo);
	}
	
	public List<IncomingsVO> incomingsPrevMonth(IncomingsVO vo) {
		System.out.println("===> mybatis�� incomingsPrevMonth() ��� ����");
		
		return mybatis.selectList("IncomingsDAO.prevMonth", vo);
	}
	
	public List<IncomingsVO> incomingsNextMonth(IncomingsVO vo) {
		System.out.println("===> mybatis�� incomingsNextMonth() ��� ����");
		
		return mybatis.selectList("IncomingsDAO.nextMonth", vo);
	}
	
	public void insertIncomings(IncomingsVO vo) {
		System.out.println("===> mybatis�� insertIncomings() ��� ����");
		
		mybatis.insert("IncomingsDAO.insertIncomings", vo);
	}
	
	public List<IncomingsVO> incomingsListCategory(IncomingsVO vo) {
		
		return mybatis.selectList("IncomingsDAO.incomingsListCategory", vo);
	}
	
	public void incomingsDelete(IncomingsVO vo) {
		
		mybatis.delete("IncomingsDAO.incomingsDelete", vo);
	}
	
	public void incomingsDeleteById(String id) {
		
		mybatis.delete("IncomingsDAO.incomingsDeleteById", id);
	}
	
	public int totalIncomingsPrice(IncomingsVO vo) {
		
		return mybatis.selectOne("IncomingsDAO.totalIncomingsPrice", vo);
	}
	
}
