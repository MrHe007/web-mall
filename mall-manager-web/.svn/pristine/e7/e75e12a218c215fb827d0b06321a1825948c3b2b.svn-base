package com.bigguy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.pojo.EasyUITreeNode;
import com.bigguy.pojo.PageTO;
import com.bigguy.pojo.TbItem;
import com.bigguy.pojo.TbItemDesc;
import com.bigguy.service.ItemDescService;
import com.bigguy.service.ItemService;
import com.bigguy.utils.IDUtils;
import com.bigguy.utils.MsgResult;

@Controller
public class ItemController {

	@Autowired
	ItemService service;
	
	@Autowired
	ItemDescService itemDescService;
	
	@RequestMapping("/item/save")
	@ResponseBody
	public MsgResult addItem(TbItem item,String desc) {
		// 
		
		MsgResult msgResult = service.addItem(item,desc);
		
		return msgResult;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public PageTO itemList(Integer page,Integer rows) {
		
		PageTO to = service.getPageTO(page, rows);
		
		return to;
	}
	
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem findItem(@PathVariable Long itemId) {
		
		TbItem item = service.findItem(itemId);
		return item;
	}
	
	
}
