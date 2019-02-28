package com.icss.hr.emp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;

public interface EmpMapper {
   
	void insert(Emp emp);
	
	void update(Emp emp);
	
	void delete(Integer empId);
	
	Emp queryById(Integer empId);
	
	/**
	 * 传入登录名,根据登录名称查询满足条件的员工数据返回（员工编号、登录名、密码，头像数据） 登录名不存在，返回null
	 * @param empLoginName
	 * @return
	 */
	Emp queryByLoginName(String empLoginName);
	
	int getCount();
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	List<Emp> queryByPage(Pager pager);
	
	/**
	 * 根据登录名更新头像
	 * @param empLoginName
	 * @param empPic
	 */
	void updateEmpPic(@Param("empLoginName")String empLoginName,@Param("empPic")String empPic);
	
	/**
	 * 修改密码
	 * @param emp
	 */
	void updatePwd(Emp emp);
	
	
	/**
	 * 根据部门编号，职务编号，员工姓名条件检索
	 */
	List<Emp> queryByCondition(@Param("deptId")Integer deptId,
			@Param("jobId")Integer jobId,@Param("empName")String empName,@Param("pager")Pager pager);	
	
	/**
	 * 满足条件的总记录数
	 */
	int getCountByCondition(@Param("deptId")Integer deptId,
			@Param("jobId")Integer jobId,@Param("empName")String empName);
	
	/**
	 * 获得主键的自动编号值
	 */
	int getPrimaryKey();
	
}