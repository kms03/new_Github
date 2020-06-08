package com.green.biz.address.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.address.AddressService;
import com.green.biz.address.AddressVO;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDAO addressDAO;

	@Override
	public List<AddressVO> addressList(String dong) {
		
		return addressDAO.addressList(dong);
	}
	
	

}
