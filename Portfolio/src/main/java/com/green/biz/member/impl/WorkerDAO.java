package com.green.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.member.WorkerVO;

@Repository
public class WorkerDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public WorkerVO getWorker(String id) {
		
		return mybatis.selectOne("WorkerDAO.getWorker", id);
	}
	
	public String loginAdmin(String id) {
		
		return mybatis.selectOne("WorkerDAO.loginAdmin", id);
	}
	
	public List<WorkerVO> adminList() {
		
		return mybatis.selectList("WorkerDAO.adminList");
	}
	
}
