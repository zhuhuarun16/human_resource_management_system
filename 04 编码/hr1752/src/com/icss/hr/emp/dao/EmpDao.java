package com.icss.hr.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工dao
 * 
 * @author DLETC
 *
 */
public class EmpDao {

	// 增
	public void insert(Emp emp) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "insert into emp values(emp_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpPwd());
		pstmt.setString(4, emp.getEmpEmail());
		pstmt.setString(5, emp.getEmpPhone());
		pstmt.setDouble(6, emp.getEmpSalary());
		pstmt.setDate(7, emp.getEmpHiredate());
		pstmt.setInt(8, emp.getDept().getDeptId());
		pstmt.setInt(9, emp.getJob().getJobId());
		pstmt.setString(10, emp.getEmpPic());
		pstmt.setString(11, emp.getEmpInfo());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	// 改
	public void update(Emp emp) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update emp set emp_name=?,emp_phone=?,emp_salary=?,"
				+ "emp_dept_id=?,emp_job_id=?,emp_info=? where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpPhone());
		pstmt.setDouble(3, emp.getEmpSalary());
		pstmt.setInt(4, emp.getDept().getDeptId());
		pstmt.setInt(5, emp.getJob().getJobId());
		pstmt.setString(6, emp.getEmpInfo());
		pstmt.setInt(7, emp.getEmpId());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();

	}

	// 删
	public void delete(int empId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "delete from emp where emp_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, empId);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();

	}

	// 查(单表emp查询)不需要部门名称和职务名称，只需要把部门id和职务id存到emp对象中即可
	public Emp queryById(int empId) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select * from emp where emp_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, empId);

		ResultSet rs = pstmt.executeQuery();

		Emp emp = null;

		if (rs.next()) {
			Dept dept = new Dept();
			dept.setDeptId(rs.getInt(9));

			Job job = new Job();
			job.setJobId(rs.getInt(10));

			emp = new Emp(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getDouble(7), rs.getDate(8), dept, job, rs.getString(11), rs.getString(12));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return emp;
	}

	/**
	 * 传入登录名,根据登录名称查询满足条件的员工数据返回（员工编号、登录名、密码，头像数据） 登录名不存在，返回null
	 * 
	 * @param empName
	 * @return
	 * @throws SQLException
	 */
	public Emp queryByLoginName(String empLoginName) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select emp_id,emp_name,emp_login_name,emp_pwd,emp_pic from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, empLoginName);

		ResultSet rs = pstmt.executeQuery();

		Emp emp = null;

		if (rs.next()) {
			emp = new Emp();
			emp.setEmpId(rs.getInt(1));
			emp.setEmpName(rs.getString(2));
			emp.setEmpLoginName(rs.getString(3));
			emp.setEmpPwd(rs.getString(4));
			emp.setEmpPic(rs.getString(5));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return emp;
	}

	/**
	 * 返回表的总记录数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getCount() throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "select count(*) from emp";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		rs.next();

		int count = rs.getInt(1);

		rs.close();
		pstmt.close();
		conn.close();

		return count;
	}

	/**
	 * 分页查询数据
	 * 
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public List<Emp> queryByPage(Pager pager) throws SQLException {

		Connection conn = DbUtil.getConnection();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM   (SELECT rownum rnum,");
		sb.append("               e.emp_id,");
		sb.append("               e.emp_name,");
		sb.append("               e.emp_login_name,");
		sb.append("               e.emp_pwd,");
		sb.append("               e.emp_email,");
		sb.append("               e.emp_phone,");
		sb.append("               e.emp_salary,");
		sb.append("               e.emp_hiredate,");
		sb.append("               d.dept_name,");
		sb.append("               j.job_name,");
		sb.append("               e.emp_info");
		sb.append("        FROM   emp e ");
		sb.append("        LEFT   OUTER JOIN dept d ON e.emp_dept_id = d.dept_id ");
		sb.append("        LEFT   OUTER JOIN job j ON e.emp_job_id = j.job_id) ");
		sb.append("WHERE rnum BETWEEN ? AND ?");

		PreparedStatement pstmt = conn.prepareStatement(sb.toString());

		System.out.println(sb);

		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getPageSize() * pager.getPageNum());

		ResultSet rs = pstmt.executeQuery();

		ArrayList<Emp> list = new ArrayList<>();

		while (rs.next()) {

			Dept dept = new Dept();
			dept.setDeptName(rs.getString(10));

			Job job = new Job();
			job.setJobName(rs.getString(11));

			Emp emp = new Emp(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getString(7), rs.getDouble(8), rs.getDate(9), dept, job, null, rs.getString(12));

			list.add(emp);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}

	/**
	 * 根据登录名更新头像数据
	 * 
	 * @throws SQLException
	 */
	public void updateEmpPic(String empLoginName, String empPic) throws SQLException {

		Connection conn = DbUtil.getConnection();

		String sql = "update emp set emp_pic=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, empPic);
		pstmt.setString(2, empLoginName);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	/**
	 * 传入emp对象，修改密码
	 * 
	 * @param emp
	 * @throws SQLException
	 */
	public void updatePwd(Emp emp) throws SQLException {
		Connection conn;
		conn = DbUtil.getConnection();
		String sql = "update emp set emp_pwd=? where emp_Login_Name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpPwd());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.executeQuery();

		pstmt.close();
		conn.close();
	}

}