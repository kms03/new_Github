package com.green.biz.incomings.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.incomings.IncomingsService;
import com.green.biz.incomings.IncomingsVO;

@Service
public class IncomingsServiceImpl implements IncomingsService {
	@Autowired
	private IncomingsDAO incomingsDAO;
	
	@Override
	public List<IncomingsVO> incomingsList(IncomingsVO vo) {
		
		return incomingsDAO.incomingsList(vo);
	}

	@Override
	public List<IncomingsVO> incomingsPrevMonth(IncomingsVO vo) {
		
		return incomingsDAO.incomingsPrevMonth(vo);
	}

	@Override
	public List<IncomingsVO> incomingsNextMonth(IncomingsVO vo) {
		
		return incomingsDAO.incomingsNextMonth(vo);
	}

	@Override
	public void insertIncomings(IncomingsVO vo) {
		
		incomingsDAO.insertIncomings(vo);
		
	}

	@Override
	public List<IncomingsVO> incomingsListCategory(IncomingsVO vo) {
		
		return incomingsDAO.incomingsListCategory(vo);
	}

	@Override
	public void incomingsDelete(IncomingsVO vo) {
		
		incomingsDAO.incomingsDelete(vo);
		
	}

	@Override
	public void incomingsDeleteById(String id) {
		
		incomingsDAO.incomingsDeleteById(id);
		
	}

	@Override
	public int totalIncomingsPrice(IncomingsVO vo) {
		
		return incomingsDAO.totalIncomingsPrice(vo);
	}

}
