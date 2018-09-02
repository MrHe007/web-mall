package com.bigguy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigguy.pojo.TbItem;
import com.bigguy.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService service;
	
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem findItem(@PathVariable Long itemId) {
		
		TbItem item = service.findItem(itemId);
		return item;
	}
	
	
}
