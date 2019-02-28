package com.icss.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.perm.service.PermService;

/**
 * 
 * 测试权限service
 *
 */
public class TestPermService {
	
	private ApplicationContext context;
	private PermService service;
	
	//@Before方法在任何@Test方法之前自动执行
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (PermService) context.getBean("permService");
	}
	
	/**
	 * 测试查询角色列表
	 */
	@Test
	public void testQueryRole() {
		
		List<HashMap<String,Object>> list = service.queryRole("tom");
		
		for (Map map : list) {
			System.out.println(map);
		}
		
	}
	
	/**
	 * 测试查询权限列表
	 */
	@Test
	public void testQueryPerm() {
		
		List<HashMap<String,Object>> list = service.queryPerm("rose");
		
		for (Map map : list) {
			System.out.println(map);
		}
		
	}

}