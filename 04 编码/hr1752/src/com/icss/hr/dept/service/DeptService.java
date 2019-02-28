package com.icss.hr.dept.service;

import java.sql.SQLException;
import java.util.List;
import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * 部门业务
 * @author DLETC
 *
 */
public class DeptService {
	
	private DeptDao dao = new DeptDao();
	
	public void addDept(Dept dept) throws SQLException {
		dao.insert(dept);
	}
	
	public void updateDept(Dept dept) throws SQLException {
		dao.update(dept);
	}
	
	public void deleteDept(int deptId) throws SQLException {
		dao.delete(deptId);
	}
	
	public Dept queryDeptById(int deptId) throws SQLException {
		return dao.queryById(deptId);
	}
	
	public List<Dept> queryDept() throws SQLException {
		return dao.query();
	}

}