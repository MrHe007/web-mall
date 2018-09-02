package com.bigguy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigguy.mapper.TbItemDescMapper;
import com.bigguy.pojo.TbItemDesc;
import com.bigguy.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	TbItemDescMapper mapper;
	
	public void addc(TbItemDesc desc) {
		
		mapper.insert(desc);
		
	}

}
