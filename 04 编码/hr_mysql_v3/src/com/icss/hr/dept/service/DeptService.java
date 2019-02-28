package com.icss.hr.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.dept.dao.DeptMapper;
import com.icss.hr.dept.pojo.Dept;

/**
 * ≤ø√≈Service
 * @author DLETC
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DeptService {
	
	@Autowired
	private DeptMapper mapper;
		
	public void addDept(Dept dept) {		
		mapper.insert(dept);
	}
	
	public void updateDept(Dept dept) {
		mapper.update(dept);
	}
	
	public void deleteDept(Integer deptId) {
		mapper.delete(deptId);
	}
	
	@Transactional(readOnly=true)
	public Dept queryDeptById(Integer deptId) {
		return mapper.queryById(deptId);
	}
	
	@Transactional(readOnly=true)
	public List<Dept> queryDept() {
		return mapper.query();
	}

}