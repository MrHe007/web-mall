package com.bigguy.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigguy.content.service.ContentCategoryService;
import com.bigguy.mapper.TbContentCategoryMapper;
import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.pojo.TbContentCategory;
import com.bigguy.pojo.TbContentCategoryExample;
import com.bigguy.pojo.TbContentCategoryExample.Criteria;
import com.bigguy.utils.MsgResult;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper mapper;
	
	public List<EasyUITreeNode> getContentCategoryList(long id) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id); 		// 加上限定条件
		
		List<TbContentCategory> list = mapper.selectByExample(example);
		
		List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
		
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId().intValue());
			node.setState(tbContentCategory.getIsParent() ? "closed" :"open" );
			node.setText(tbContentCategory.getName());
		
			easyUITreeNodes.add(node); 		// 添加到集合中
		}
		
		return easyUITreeNodes;
	}

	@Override
	public MsgResult addNewNode(Long parentId, String name) {

		TbContentCategory newNode = new TbContentCategory();
		newNode.setParentId(parentId);
		newNode.setName(name);
		newNode.setSortOrder(1);
		
		newNode.setIsParent(false); 	// 新建结点为 false
		newNode.setStatus(1); 			// 1 表示正常  可选值:1(正常),2(删除)
		newNode.setCreated(new Date());
		newNode.setUpdated(new Date());
		
		// 向表中插入数据
		
		mapper.insert(newNode);
		
		TbContentCategory parentNode = mapper.selectByPrimaryKey(parentId); 		// 这个新建结点的父节点
		
		if(!parentNode.getIsParent()) {
			// 如果这个结点本来不是父节点，现在增加一个子节点，表成了新结点
			parentNode.setIsParent(true);
			mapper.updateByPrimaryKey(parentNode); 		// 修改结点
		}
		
		return MsgResult.ok(newNode); 			// 因为在 mapper.xml 配置了插入元素后，自动添加主键，所以得到的对象有 id 属性 
	}

	
	
}
