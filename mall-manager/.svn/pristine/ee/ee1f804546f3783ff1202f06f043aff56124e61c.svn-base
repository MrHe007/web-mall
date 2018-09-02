package com.bigguy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigguy.mapper.TbItemCatMapper;
import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.pojo.TbItemCat;
import com.bigguy.pojo.TbItemCatExample;
import com.bigguy.pojo.TbItemCatExample.Criteria;
import com.bigguy.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	TbItemCatMapper mapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {

		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);  		// 加上限定条件
		
		List<TbItemCat> list = mapper.selectByExample(example);
		
		List<EasyUITreeNode> mainList = new ArrayList<>();
		
		for (TbItemCat itemCat : list) {
			
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId().intValue());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");
			mainList.add(node); 
			
		}
		return mainList;
	}

	
}
