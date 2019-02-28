package com.icss.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工dao测试
 * @author DLETC
 *
 */
public class TestEmpDao {
	
	private EmpDao dao = new EmpDao();
	
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(66);
		
		Job job = new Job();
		job.setJobId(23);		
		
		for(int i=50;i <= 70; i++){
			Emp emp = new Emp("wangwu"+ i,"wangwu"+ i,"123456",
					"wang@163.com","13012345678",3600,Date.valueOf("1995-10-21"),dept,job,null,"精通java,oracle");
			
			dao.insert(emp);
		}		
	}
	
	@Test
	public void testInsertMany() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(5);
		
		Job job = new Job();
		job.setJobId(1);	
		
		for (int i = 30;i <= 60;i ++) {
			Emp emp = new Emp("jack" + i,"jack" + i,"123456",
					"jack@163.com","13012345678",4700,Date.valueOf("2000-10-1"),dept,job,null,"精通java,oracle");
			
			dao.insert(emp);
		}		
				
	}
		
	@Test
	public void testUpdate() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(1);
		
		Job job = new Job();
		job.setJobId(2);		
		
		Emp emp = new Emp(5,"wangwu11","wangwu11","111111",
				"wang111@163.com","13011111111",1111,Date.valueOf("1995-11-11"),dept,job,null,"1111");
		
		dao.update(emp);		
	}
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(5);
		
	}
	
	@Test
	public void testQueryById() throws SQLException {
		
		Emp emp = dao.queryById(222);
		System.out.println(emp);
		
	}
	
	@Test
	public void testQueryByLoginName() throws SQLException {
		
		Emp emp = dao.queryByLoginName("tom");
		System.out.println(emp);
		
	}
	
	@Test
	public void testGetCount() throws SQLException {
		
		int count = dao.getCount();
		System.out.println(count);
		
	}
	
	@Test
	public void testQueryByPage() throws SQLException {
		
		Pager pager = new Pager(dao.getCount(), 10, -4);
		
		List<Emp> list = dao.queryByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}		
		
	}
	
	@Test
	public void testUpdateEmpPic() throws SQLException {
		
		dao.updateEmpPic("tom", "abcdefg");
		
	}
	
	
}