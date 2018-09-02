package com.bigguy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class TestList {
	
	@Test
	public void test0190() {
		
		String str ="abc";
		
		String s1 = "abc";
		
		System.out.println(str.hashCode());	 //  96354
		
		System.out.println(s1.hashCode()); //  96354         

		//  String 如果字符串相等，则 hashCode()  和 equals() 相等
	}
	
	@Test
	public void test01() {
		
		Student s1 = new Student("jeck",23);
		
		Student s2 = new Student("jeck",23); 
		
		System.out.println(s1.hashCode() == s2.hashCode()); 
		
		HashMap<Student,String> map = new HashMap<>();
		
		map.put(s1, "jeck");
		map.put(s2,"tom");
		System.out.println(map.get(s1) == map.get(s2)); 		// false , equals()不同 
	}
	
	@Test
	public void test03() {
		
		Set<String> set = new HashSet<>();
		
		set.add("jeck");
		set.add("tom");
		set.add("rose");
		
	/*	Iterator<String> iter = set.iterator();
		
		while(iter.hasNext()) {
			String elem = (String)iter.next();
			System.out.println(elem);
		}*/
		
		// for 循环这种遍历要好一点，因为此时 定义迭代器 iter 是局部变量，易于回收 
		for(Iterator<String> iter = set.iterator(); iter.hasNext();) { 
			String elem = (String)iter.next();
			System.out.println(elem);
		}
	}

}
