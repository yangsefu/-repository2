package com.clive.demo1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ProxySelector;

import org.junit.Test;

import com.clive.service.StudentService;
import com.clive.service.TeacherService;
import com.clive.service.impl.StudentServiceImpl;
import com.clive.service.impl.TeacherServiceImpl;
import com.clive.service.impl.StudentServiceImpl;

public class MyTest {
      /**反射
       * 通过反射我们可以访问任意一个类里面的私有属性和方法
       * 之前我们通过new 关键字创建对象
       * 但是再spring不用new 
    * spring springmvc mybatis 所有的框架都用了一种技术：反射
       * 反射可以不通过new创建对象 而且可以调用这个对象中的所有方法 
       * 万物皆对象 任何一个.java文件都会被编译成.class文件 叫做Class类 
       * 
       * 
       * 
       * @throws Exception
       */
	@Test
	public void demo1() throws Exception   {
//		传入一个全类名加载 得到一个类的Class类  此处我们只是得到了Student的Class对象 还没有调用其构造方法
		Class studentClass = Class.forName("com.clive.demo1.Student");
//		调用Class对象中的构造方法来创建对象
		Object newInstance = studentClass.newInstance();
		//     Field field = studentClass.getField("age");
//		得到int类型属性用getInt String类型的用get
		//     System.out.println(Field.getInt(newInstance));
		//     获取私有的属性 必须加上Declared
		Field declaredField = studentClass.getDeclaredField("name");
		//     并且开启暴力访问模式
		declaredField.setAccessible(true);
		System.out.println(declaredField.get(newInstance));
	}
	@Test
	public void show2() throws Exception {
		Class studentClass = Class.forName("com.clive.demo1.Student");
		Object newInstance = studentClass.newInstance();
		Method method = studentClass.getMethod("show");
		/**
		 * Class对象.getMeThod()调用指定方法
		 *  方法对象.invoke() 调用指定方法
		 */
		method.invoke(newInstance);
	}
	@Test
	public void show3() throws Exception {
		Class studentClass = Class.forName("com.clive.demo1.Student");
		Object newInstance = studentClass.newInstance();
		//		访问私有方法
		Method method = studentClass.getDeclaredMethod("show2",int.class);
		method.setAccessible(true);
		method.invoke(newInstance, 999);
	}
	@Test
	public void demo4() {
		StudentService service = new StudentServiceImpl();
		service.addStudent();
	}
	/**
	 * 
	 * 实际开发中项目更新 一个service不会只有几个方法
	   我们分别要在很多个方法里修改代码  很费功夫
	   为了解决这个问题 我们使用动态代理
	   只写一份代码 可以把所有service里面的方法全部加上（范围可控）
	 * 
	 */	

		@Test
		public void show5() {
	/**动态代理
	 * 1.一定要使用多态 原本的对象
	 * 2.把原本的对象变成动态代理对象 变成后不用再原本的对象里做修改代码
	 * 
	 */
			StudentService service = new StudentServiceImpl();
			StudentService proxyService = (StudentService) Proxy.newProxyInstance(
//			           对象类的加载器		
		            service.getClass().getClassLoader(), 
//		          Class接口对象
					service.getClass().getInterfaces(),
//                一个invocationHandler接口的子类对象 在这里面做逻辑处理	
					new MyInvocationHandler(service));
//	    此处本应执行StudentServiceImpl的代码 但是由于是动态代理对象调用add方法
//	    不会取执行StudentServiceImpl的代码 
//	     而是去执行了 MyInvocationHandler里面的invoke方法
//	   
			proxyService.addStudent();
			proxyService.deleteStudent();
		}
	@Test
	public void demo6() {
		TeacherService service = new TeacherServiceImpl();
		TeacherService proxyTeacher= (TeacherService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
				service.getClass().getInterfaces(),
				new MyInvocationHandler(service));
		proxyTeacher.addTeacher();
	}
}
