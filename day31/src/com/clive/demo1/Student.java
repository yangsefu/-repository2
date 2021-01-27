package com.clive.demo1;

public class Student {
	public int age = 10;
	private String name = "李磊";

	public Student() {
		System.out.println("我是无参构造 是公共访问规则");
	}



	private Student(int i) {
		System.out.println("我是有参构造方法"+i);
	}
	public void show() {
		System.out.println("我是公共普通方法");
	}
	private void show2(int i) {
		System.out.println("我是私有普通有参方法");
	}
}
