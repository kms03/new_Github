package com.green.biz.member;

import java.util.List;

public interface WorkerService {
	
	WorkerVO getWorker(String id);
	
	int loginAdmin(String id, String pwd);
	
	List<WorkerVO> adminList();
}
