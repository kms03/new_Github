package com.green.biz.outgoings.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.outgoings.OutgoingsService;
import com.green.biz.outgoings.OutgoingsVO;
import com.green.biz.util.SearchCriteria;

@Service
public class OutgoingsServiceImpl implements OutgoingsService {
	@Autowired
	private OutgoingsDAO outgoingsDAO;
	
	@Override
	public List<OutgoingsVO> getOutgoingsList(OutgoingsVO vo) {
		
		return outgoingsDAO.getOutgoingsList(vo);
	}

	@Override
	public List<OutgoingsVO> prevMonth(OutgoingsVO vo) {
		
		return outgoingsDAO.prevMonth(vo);
	}

	@Override
	public OutgoingsVO getOutgoings(OutgoingsVO vo) {
		
		return outgoingsDAO.getOutgoings(vo);
	}

	@Override
	public List<OutgoingsVO> nextMonth(OutgoingsVO vo) {
		
		return outgoingsDAO.nextMonth(vo);
	}

	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		
		return outgoingsDAO.listCount(scri);
	}

	@Override
	public void insertOutgoings(OutgoingsVO vo) {
		
		outgoingsDAO.insertOutgoings(vo);
		
	}

	@Override
	public List<OutgoingsVO> getOutgoingsCategoryList(OutgoingsVO vo) {
		
		return outgoingsDAO.getOutgoingsCategoryList(vo);
	}

	@Override
	public void outgoingsDelete(OutgoingsVO vo) {
		
		outgoingsDAO.outgoingsDelete(vo);
		
	}

	@Override
	public List<OutgoingsVO> scriOutgoingsList(SearchCriteria scri) throws Exception {
		
		return outgoingsDAO.scriOutgoingsList(scri);
	}

	@Override
	public int outListCount(SearchCriteria scri) throws Exception {
		
		return outgoingsDAO.outListCount(scri);
	}

	@Override
	public void outgoingsDeleteById(String id) {
		
		outgoingsDAO.outgoingsDeleteById(id);
		
	}

	@Override
	public int totalOutgoingsPrice(OutgoingsVO vo) {
		
		return outgoingsDAO.totalOutgoingsPrice(vo);
	}

}
