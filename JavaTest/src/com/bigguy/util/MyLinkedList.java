package com.bigguy.util;

public class MyLinkedList {
	
	// 通过链表形式对象

	class Node{
		private Object data;
		private Node next; 
		
		
		public Node() {
			
		}
		public Node(Object data) {
			this.data = data;
		}
	}
	
	private Node head; 	// 头结点 ， 初始值为 null
	
	private Node tail;  // 尾节点
	
	private int size; 	// 表示 List 里面的元素的个数
	
	public MyLinkedList() {
		size = 0; 				// 显示的赋值为　0 ,可以不赋初值
	}
	
	public int size() {
		return size;
	}
	
	public void checkIndex(int index) throws Exception {
		if(index<0 || index>size) {
			throw new Exception("越界1");
		}
	}
	
	public Object get(int index) throws Exception {
		
		Node node = head; 	// 指向首节点
		int i = 1;
		while(i<index && i<size) {
			i++;
			node = node.next;
		}
		
		return node.data;
	}
	
	
	public void remove(Object data) {
		// 从头往前循环，如果找到该元素，就将该元素去掉
		
	}
	
	public void add(Object data,int index) {
		
		int i = 0;
		
		Node node = head.next;
		
		while(i<index) {
			
		}
	}
	
	public void add(Object data) {
		
		if(head==null) { 	// 表示是
			head = new Node(data);
			tail = head;
		}else {  	// 在尾部增加元素
			Node newNode = new Node(data);
			tail.next = newNode; 		
			tail = newNode;
		}
		size++; 		/// 添加元素
	}
	
}
