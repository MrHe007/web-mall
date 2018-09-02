package com.bigguy.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigguy.jedis.JedisClientPool;

import redis.clients.jedis.JedisCluster;

public class TestJedis {

	@Test
	public void test01() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		
//		JedisClientPool jedisClientPool = context.getBean(JedisClientPool.class);
		
		
//		String string = jedisClientPool.get("name");
		
		JedisCluster cluster = context.getBean(JedisCluster.class);
		
		String string = cluster.get("name");
		
		System.out.println(string);
		
	}
}
