package com.green.biz.outgoings;

import java.sql.Date;
import java.util.List;

import com.green.biz.util.SearchCriteria;

public interface OutgoingsService {
	
	List<OutgoingsVO> getOutgoingsList(OutgoingsVO vo);
	
	List<OutgoingsVO> prevMonth(OutgoingsVO vo);
	
	OutgoingsVO getOutgoings(OutgoingsVO vo);
	
	List<OutgoingsVO> nextMonth(OutgoingsVO vo);
	
	int listCount(SearchCriteria scri) throws Exception;
	
	void insertOutgoings(OutgoingsVO vo);
	
	List<OutgoingsVO> getOutgoingsCategoryList(OutgoingsVO vo);
	
	void outgoingsDelete(OutgoingsVO vo);
	
	List<OutgoingsVO> scriOutgoingsList(SearchCriteria scri) throws Exception;
	
	int outListCount(SearchCriteria scri) throws Exception;
	
	void outgoingsDeleteById(String id);
	
	int totalOutgoingsPrice(OutgoingsVO vo);
	
}
