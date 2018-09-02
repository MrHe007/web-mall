package com.bigguy.content.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigguy.content.service.IContentService;
import com.bigguy.mapper.TbContentMapper;
import com.bigguy.pojo.PageTO;
import com.bigguy.pojo.TbContent;
import com.bigguy.pojo.TbContentExample;
import com.bigguy.pojo.TbContentExample.Criteria;
import com.bigguy.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private TbContentMapper mapper;
	
	public MsgResult addContent(TbContent content) {

		mapper.insert(content);
		
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		return MsgResult.ok(content);
	}

	public PageTO queryList(Long categoryId,int pageNum,int pageSize) {

		PageHelper.startPage(pageNum, pageSize); 		// 会 拦截
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> contentList = mapper.selectByExample(example);
		
		
		PageTO pageTo = new PageTO();
		
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		
		pageTo.setTotal((int) pageInfo.getTotal());
		pageTo.setRows(contentList); 		// 表示每行的数据 
		
		return pageTo;
	}

}
