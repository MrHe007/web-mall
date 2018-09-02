package com.bigguy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.content.service.IContentService;
import com.bigguy.pojo.PageTO;
import com.bigguy.pojo.TbContent;
import com.bigguy.utils.MsgResult;

@Controller
public class ContentController {

	@Autowired
	IContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public PageTO queryList(Long categoryId,int page,int rows) {
		
		PageTO pageTO = contentService.queryList(categoryId,page, rows);
		
		return pageTO;
	}
	
	
	@RequestMapping(value="/content/save")
	@ResponseBody
	public MsgResult addContent(TbContent content) {
		
		MsgResult msgResult = contentService.addContent(content);
		
		return msgResult;
	}
	
}
