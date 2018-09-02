package com.bigguy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	ItemCatService service;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id",defaultValue="0") Long parentId) {
		// 如果没带参数，默认是顶层结点
		
		List<EasyUITreeNode> list = service.getItemCatList(parentId);
		
		return list;
	}
	
}
