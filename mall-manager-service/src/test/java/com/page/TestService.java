package com.page;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @Description:    TODO
 * @author: bigguy-hc 
 * @date:   2018年8月28日 上午10:32:19   
 */
public class TestService {
	
	@Test
	public void test() throws InterruptedException {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		while(true) {
			Thread.sleep(1000);
		}
		
	}

}
