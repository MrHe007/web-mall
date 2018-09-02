package com.bigguy;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class Test01 {

	
	@Test
	public void tets01() {
		StringBuilder str = new StringBuilder(); 	// 初始长度为 16 char ch = new char[16];
		
		str.append(true); 			// 重载了很多append()方法，可以直接添加 true,false....其他类型
		
		str.append("jeck").append("tom"); 		// 可以使用方法链，因为每次 append()返回的是 StringBuilder
		
		str.append("abc"); // 
		
		System.out.println(str);  // truejecktomabc
	}
	
	@Test
	public void tets02() {
	
		int [][] arr= { 		// 静态初始化
				{1,2,3},
				{2,3},
				{4,5,6,8}
		};

		System.out.println(arr.length); 		// 表示有几行
		
		System.out.println(arr);		// [[I@225ac214 有两个[[ ， 表示是一个二维数组的引用
		System.out.println(arr[0]);		// [I@747bc0aa , 只有一个 [ , 表示是以为数组 
		
		int [][]arr2 = new int[3][]; 		// 表示有3行，但是列数是动态的，可以任意
		
		arr2[0] = new int[10];
		arr2[1]  = new int[4]; 			
		// 类似于c语言的 指针，将 new int[] 的指针指向 aee[2] ,所以可以想象，二维数组其实就是一个一维数组在，这个一维数组的每个元素指向一个一维数组的首地址
	}
	
	@Test
	public void test03() {
		
		/**
		    System.arraycopy(src, srcPos, dest, destPos, length);
		 *  将 src 数组里面的元素复制到 dest 下面 ， 复制的元素的个数为 length
		 *  复制的起点为 srcPos  , 终点为 srcPos + length-1;  
		 *  dest 从 destPos 位置开始接受 length 个 元素， 
		 */
		
		int []arr = {1,2,3,4,5,6};
		
		int []arr2 = new int[10];
		System.arraycopy(arr, 1, arr2, 2, 5);
		
		System.out.println(arr2);
		
		for(int j=0; j<10; j++) {
			System.out.println("i = "+j+"  "+arr2[j]);
		}
		/*输出 
		 *  i = 0  0
			i = 1  0
			i = 2  2
			i = 3  3
			i = 4  4
			i = 5  5
			i = 6  6
			i = 7  0
			i = 8  0
			i = 9  0
		 */
	}
	
	@Test
	public void test04() {
		
		
		
	}
	
}
