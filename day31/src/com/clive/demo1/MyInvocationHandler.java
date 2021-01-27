package com.clive.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.clive.service.StudentService;
import com.clive.service.TeacherService;

public class MyInvocationHandler implements  InvocationHandler{
	private TeacherService service;
	public MyInvocationHandler(TeacherService service) {
		this.service = service;
	}
	@Override
	public Object invoke(Object proxy, 
			Method method, Object[] args) throws Throwable {
		System.out.println("���Ǵ��� ����ִ�д���");
		System.out.println("������¼");
		method.invoke(service);
		System.out.println("�ύ��¼");
		return null;
	}

}
