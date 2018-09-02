package com.bigguy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.content.service.ContentCategoryService;
import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.utils.MsgResult;

@Controller
public class ContentCategoryController {
	
	@Autowired
	ContentCategoryService contentCategoryService;

	
	@RequestMapping(value ="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public MsgResult addNewNode(Long parentId,String name) {
		
		MsgResult msgResult = contentCategoryService.addNewNode(parentId, name);
		
		return msgResult;
	}
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0")Long id){
		
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(id);
		
		return list;
	}

	
	
}
