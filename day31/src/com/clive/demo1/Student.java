package com.clive.demo1;

public class Student {
	public int age = 10;
	private String name = "����";

	public Student() {
		System.out.println("�����޲ι��� �ǹ������ʹ���");
	}



	private Student(int i) {
		System.out.println("�����вι��췽��"+i);
	}
	public void show() {
		System.out.println("���ǹ�����ͨ����");
	}
	private void show2(int i) {
		System.out.println("����˽����ͨ�вη���");
	}
}
