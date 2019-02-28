package com.icss.hr.emp.pojo;

import java.sql.Date;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.pojo.Job;

/**
 * 员工实体类
 * 
 * @author DLETC
 *
 */
public class Emp {

	private int empId;

	private String empName;

	private String empLoginName;

	private String empPwd;

	private String empEmail;

	private String empPhone;

	private double empSalary;

	private Date empHiredate;

	private Dept dept;// 多对一

	private Job job;// 多对一

	private String empPic;

	private String empInfo;

	public Emp() {
		super();
	}

	public Emp(String empName, String empLoginName, String empPwd, String empEmail, String empPhone, double empSalary,
			Date empHiredate, Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.empPhone = empPhone;
		this.empSalary = empSalary;
		this.empHiredate = empHiredate;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public Emp(int empId, String empName, String empLoginName, String empPwd, String empEmail, String empPhone,
			double empSalary, Date empHiredate, Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.empPhone = empPhone;
		this.empSalary = empSalary;
		this.empHiredate = empHiredate;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLoginName() {
		return empLoginName;
	}

	public void setEmpLoginName(String empLoginName) {
		this.empLoginName = empLoginName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public Date getEmpHiredate() {
		return empHiredate;
	}

	public void setEmpHiredate(Date empHiredate) {
		this.empHiredate = empHiredate;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getEmpPic() {
		return empPic;
	}

	public void setEmpPic(String empPic) {
		this.empPic = empPic;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empLoginName=" + empLoginName + ", empPwd=" + empPwd
				+ ", empEmail=" + empEmail + ", empPhone=" + empPhone + ", empSalary=" + empSalary + ", empHiredate="
				+ empHiredate + ", dept=" + dept + ", job=" + job + ", empPic=" + empPic + ", empInfo=" + empInfo + "]";
	}
		
}
