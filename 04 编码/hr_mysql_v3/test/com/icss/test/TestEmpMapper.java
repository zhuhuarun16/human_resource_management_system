package com.icss.test;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpMapper;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 测试Emp dao
 * 
 * @author DLETC
 *
 */
public class TestEmpMapper {

	private ApplicationContext context;
	private EmpMapper mapper;

	// @Before方法在任何@Test方法之前自动执行
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		mapper = (EmpMapper) context.getBean(EmpMapper.class);
	}
	
	@Test
	public void testInsert() {
		
		Dept dept = new Dept();
		dept.setDeptId(1);
		
		Job job = new Job();
		job.setJobId(7);
		
		Emp emp = new Emp("tom","tom","123456","tom@163.com",
				"13812345678",3999.0,Date.valueOf("2017-11-20"),dept,job,"擅长java oracle mysql web前端");
		
		mapper.insert(emp);		
	}
	
	@Test
	public void testInsertMany() {
		
		Dept dept = new Dept();
		dept.setDeptId(1);
		
		Job job = new Job();
		job.setJobId(7);
		
		for (int i = 1;i <= 20;i ++) {
			
			Emp emp = new Emp("jack" + i,"jack" + i,"123456","jack@163.com",
					"13812345678",4999.0,Date.valueOf("2017-11-21"),dept,job,"擅长java oracle mysql web前端");
			
			mapper.insert(emp);		
		}
	}

}