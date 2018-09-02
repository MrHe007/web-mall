package com.bigguy;

public abstract class Animal {
	
	// 抽象类中可以定义构造方法
	public Animal() {
		System.out.println("Animal constructor!");
	}
	// 抽象方法不需要定义方法体
	public abstract void run(); 
	
	// 抽象方法中可以定义普通方法，并定义方法体
	public void breath() {
		System.out.println("呼吸");
	}
	
	public static void main(String[] args) {
		
		Animal dog = new Dog();
		System.out.println(dog);
		
	}
}

class Dog extends Animal{

	public Dog() {
		System.out.println("Dog constructor!");
	}
	
	@Override
	public void run() {
		System.out.println("Dog run!");
	}
}
