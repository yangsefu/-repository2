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
		System.out.println("我是代理 我来执行代码");
		System.out.println("开启记录");
		method.invoke(service);
		System.out.println("提交记录");
		return null;
	}

}
