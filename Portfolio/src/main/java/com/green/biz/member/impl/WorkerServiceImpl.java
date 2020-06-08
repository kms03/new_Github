package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.member.WorkerService;
import com.green.biz.member.WorkerVO;

@Service
public class WorkerServiceImpl implements WorkerService {
	@Autowired
	private WorkerDAO workerDAO;
	
	@Override
	public WorkerVO getWorker(String id) {
		
		return workerDAO.getWorker(id);
	}

	@Override
	public int loginAdmin(String id, String pwd) {
		int result = -1;
		
		String worker = workerDAO.loginAdmin(id);
		
		if(worker != null) {
			if(worker.equals(pwd)) {
				result = 1;
			} else {
				result = 0;
			}
		} else {
			result = -1;
		}
		
		return result;
	}

	@Override
	public List<WorkerVO> adminList() {
		
		return workerDAO.adminList();
	}

}
