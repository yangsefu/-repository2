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
      /**����
       * ͨ���������ǿ��Է�������һ���������˽�����Ժͷ���
       * ֮ǰ����ͨ��new �ؼ��ִ�������
       * ������spring����new 
    * spring springmvc mybatis ���еĿ�ܶ�����һ�ּ���������
       * ������Բ�ͨ��new�������� ���ҿ��Ե�����������е����з��� 
       * ����Զ��� �κ�һ��.java�ļ����ᱻ�����.class�ļ� ����Class�� 
       * 
       * 
       * 
       * @throws Exception
       */
	@Test
	public void demo1() throws Exception   {
//		����һ��ȫ�������� �õ�һ�����Class��  �˴�����ֻ�ǵõ���Student��Class���� ��û�е����乹�췽��
		Class studentClass = Class.forName("com.clive.demo1.Student");
//		����Class�����еĹ��췽������������
		Object newInstance = studentClass.newInstance();
		//     Field field = studentClass.getField("age");
//		�õ�int����������getInt String���͵���get
		//     System.out.println(Field.getInt(newInstance));
		//     ��ȡ˽�е����� �������Declared
		Field declaredField = studentClass.getDeclaredField("name");
		//     ���ҿ�����������ģʽ
		declaredField.setAccessible(true);
		System.out.println(declaredField.get(newInstance));
	}
	@Test
	public void show2() throws Exception {
		Class studentClass = Class.forName("com.clive.demo1.Student");
		Object newInstance = studentClass.newInstance();
		Method method = studentClass.getMethod("show");
		/**
		 * Class����.getMeThod()����ָ������
		 *  ��������.invoke() ����ָ������
		 */
		method.invoke(newInstance);
	}
	@Test
	public void show3() throws Exception {
		Class studentClass = Class.forName("com.clive.demo1.Student");
		Object newInstance = studentClass.newInstance();
		//		����˽�з���
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
	 * ʵ�ʿ�������Ŀ���� һ��service����ֻ�м�������
	   ���Ƿֱ�Ҫ�ںܶ���������޸Ĵ���  �ܷѹ���
	   Ϊ�˽��������� ����ʹ�ö�̬����
	   ֻдһ�ݴ��� ���԰�����service����ķ���ȫ�����ϣ���Χ�ɿأ�
	 * 
	 */	

		@Test
		public void show5() {
	/**��̬����
	 * 1.һ��Ҫʹ�ö�̬ ԭ���Ķ���
	 * 2.��ԭ���Ķ����ɶ�̬������� ��ɺ�����ԭ���Ķ��������޸Ĵ���
	 * 
	 */
			StudentService service = new StudentServiceImpl();
			StudentService proxyService = (StudentService) Proxy.newProxyInstance(
//			           ������ļ�����		
		            service.getClass().getClassLoader(), 
//		          Class�ӿڶ���
					service.getClass().getInterfaces(),
//                һ��invocationHandler�ӿڵ�������� �����������߼�����	
					new MyInvocationHandler(service));
//	    �˴���Ӧִ��StudentServiceImpl�Ĵ��� ���������Ƕ�̬����������add����
//	    ����ȡִ��StudentServiceImpl�Ĵ��� 
//	     ����ȥִ���� MyInvocationHandler�����invoke����
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
