package com.page;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigguy.mapper.TbItemMapper;
import com.bigguy.pojo.TbItem;
import com.bigguy.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageTest {

	@Test
	public void test01() {
		
		// 初始化 spring 容器
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		
		PageHelper.startPage(1,10);
		
		
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = mapper.selectByExample(example);
		
		PageInfo<TbItem> listInfo = new PageInfo<>(list);
		
		System.out.println(listInfo.getTotal());
		System.out.println(listInfo.getSize());
		System.out.println(listInfo.getPageNum());
		System.out.println(listInfo.getPages());
		
		
		System.out.println(list);
		
	}
	
}
