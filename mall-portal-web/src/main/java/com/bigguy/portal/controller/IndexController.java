package com.bigguy.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Description:    展示首页
 * @author: bigguy-hc 
 * @date:   2018年8月28日 上午9:42:05   
 */

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String toIndexPage() {
		// 跳到主页之前，先设置一些数据，展示图片轮播的数据还有其他数据 
		// 前端的数据都是动态的 
		
		
		
		return "index";
	}
}

