package com.green.biz.incomings;

import java.util.List;

public interface IncomingsService {
	
	List<IncomingsVO> incomingsList(IncomingsVO vo);
	
	List<IncomingsVO> incomingsPrevMonth(IncomingsVO vo);
	
	List<IncomingsVO> incomingsNextMonth(IncomingsVO vo);
	
	void insertIncomings(IncomingsVO vo);
	
	List<IncomingsVO> incomingsListCategory(IncomingsVO vo);
	
	void incomingsDelete(IncomingsVO vo);
	
	void incomingsDeleteById(String id);
	
	int totalIncomingsPrice(IncomingsVO vo);
}
