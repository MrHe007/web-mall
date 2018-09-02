package com.page;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	
	@Test
	public void test() {
		
		Jedis jedis = new Jedis("192.168.25.130", 6379);
		
		String string = jedis.set("name", "bigguy");
		
		System.out.println(string);
		
		String result = jedis.get("name");
		
		System.out.println(result);
		
		jedis.close();
		
	}

	@Test
	public void test01() {
		
		JedisPool pool = new JedisPool("192.168.25.130", 6379);
		
		Jedis jedis = pool.getResource();
		
		String string = jedis.get("name");
		
		System.out.println(string);
		
		jedis.close();
		
		pool.close();
	}
	
	@Test
	public void test03() {
		
		Set<HostAndPort> nodes= new HashSet<>();
		
		nodes.add(new HostAndPort("192.168.25.130", 7001));
		nodes.add(new HostAndPort("192.168.25.130", 7002));
		nodes.add(new HostAndPort("192.168.25.130", 7003));
		nodes.add(new HostAndPort("192.168.25.130", 7004));
		nodes.add(new HostAndPort("192.168.25.130", 7005));
		nodes.add(new HostAndPort("192.168.25.130", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		jedisCluster.set("key", "the value");
		
		String string = jedisCluster.get("key");
		
		System.out.println(string);
		
		jedisCluster.close();
		
	}
	
}
