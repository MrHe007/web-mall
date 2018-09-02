package com.bigguy.service;

import java.util.List;

import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.pojo.PageTO;
import com.bigguy.pojo.TbItem;
import com.bigguy.utils.MsgResult;

public interface ItemService {

	public TbItem findItem(Long id);
	
	public PageTO getPageTO(Integer pageNum,Integer pageSize);
	
	public MsgResult addItem(TbItem item, String desc);

}
