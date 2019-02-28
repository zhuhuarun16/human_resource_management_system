package com.icss.hr.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.common.Pager;
import com.icss.hr.common.ShiroRealm;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.pojo.EmpDto;
import com.icss.hr.emp.service.EmpService;

/**
 * 员工控制器
 * 
 * @author DLETC
 *
 */
@Controller
public class EmpController {

	@Autowired
	private EmpService service;

	@Autowired
	private ShiroRealm shiroRealm;

	/**
	 * 判断用户名是否存在
	 * 
	 * @param empLoginName
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emp/checkLoginName")
	public String checkLoginName(String empLoginName, HttpServletRequest request, HttpServletResponse response) {

		Emp emp = service.queryEmpByLoginName(empLoginName);

		if (emp == null)
			return "yes";
		else
			return "no";
	}

	/**
	 * 增加员工
	 * 
	 * @param emp
	 * @throws IOException
	 */
	@RequestMapping("/emp/add")
	public void addEmp(Emp emp, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(emp);
		service.addEmp(emp);
	}

	/**
	 * 分页查询员工数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/emp/query")
	@ResponseBody
	public HashMap<String, Object> queryEmp(String pageNum, String pageSize, HttpServletRequest request,
			HttpServletResponse response) {

		int pageNumInt = 1;

		try {
			pageNumInt = Integer.parseInt(pageNum);
		} catch (Exception e) {

		}

		int pageSizeInt = 10;

		try {
			pageSizeInt = Integer.parseInt(pageSize);
		} catch (Exception e) {

		}

		// 分页对象
		Pager pager = new Pager(service.getEmpCount(), pageSizeInt, pageNumInt);

		// 获得当前页的数据集合
		List<Emp> list = service.queryEmpByPage(pager);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 删除员工
	 * 
	 * @param empId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/emp/delete")
	public void deleteEmp(Integer empId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		service.deleteEmp(empId);

	}

	/**
	 * 查询单条员工数据
	 */
	@RequestMapping("/emp/get")
	@ResponseBody
	public Emp getEmp(Integer empId, HttpServletRequest request, HttpServletResponse response) {
		Emp emp = service.queryEmpById(empId);
		return emp;
	}

	/**
	 * 修改员工数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/emp/update")
	@ResponseBody
	public void updateEmp(Emp emp, HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.updateEmp(emp);
	}

	/**
	 * 获得当前登录名称
	 */
	@RequestMapping("/emp/getLoginName")
	@ResponseBody
	public String getLoginName(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		String empLoginName = (String) session.getAttribute("empLoginName");

		return empLoginName;
	}

	/**
	 * 获得当前用户密码
	 */
	@RequestMapping("/emp/getPwd")
	@ResponseBody
	public String getPwd(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		Emp emp = service.queryEmpByLoginName(empLoginName);

		return emp.getEmpPwd();
	}

	/**
	 * 根据当前登录名返回员工信息
	 */
	@RequestMapping("/emp/queryByLoginName")
	@ResponseBody
	public Emp queryEmpByLoginName(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		Emp emp = service.queryEmpByLoginName(empLoginName);

		return emp;
	}

	/**
	 * 登录验证
	 */
	@RequestMapping("/emp/login")
	@ResponseBody
	public String login(String empLoginName, String empPwd, HttpServletRequest request, HttpServletResponse response) {

		// 封装用户名和密码
		UsernamePasswordToken token = new UsernamePasswordToken(empLoginName, empPwd);
		// 设置RememberMe
		token.setRememberMe(true);

		/* 通过shiro登录 */
		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) { // 用户名不存在
			return "1";
		} catch (IncorrectCredentialsException e) { // 密码错误
			return "2";
		}

		/* 验证成功 */

		// 获得当前登陆员工的id数据
		Emp emp = (Emp) service.queryEmpByLoginName(empLoginName);

		// 在session范围存储数据
		HttpSession session = request.getSession();
		session.setAttribute("empLoginName", empLoginName);
		session.setAttribute("empId", emp.getEmpId());

		return "3";
	}

	/**
	 * 返回当前用户头像base64数据
	 */
	@RequestMapping("/emp/queryPic")
	@ResponseBody
	public String queryEmpPic(HttpServletRequest request, HttpServletResponse response) {

		// 从session获得用户登录名称
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		// 返回头像数据
		Emp emp = service.queryEmpByLoginName(empLoginName);

		return emp.getEmpPic();
	}

	/**
	 * 更新用户头像
	 */
	@RequestMapping("/emp/updatePic")
	public void updateEmpPic(String empPic, HttpServletRequest request, HttpServletResponse response) {

		// 从session获得用户登录名称
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		// 更新头像数据
		service.updateEmpPic(empLoginName, empPic);
	}

	/**
	 * 更新密码
	 */
	@RequestMapping("/emp/updatePwd")
	public void updatePwd(String empPwd, HttpServletRequest request, HttpServletResponse response) {

		// 获得用户名
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		// 封装pojo对象
		Emp emp = new Emp();
		emp.setEmpLoginName(empLoginName);
		emp.setEmpPwd(empPwd);

		// 修改密码
		service.updateEmpPwd(emp);
	}

	/**
	 * 条件检索员工
	 */
	@RequestMapping("/emp/queryByCondition")
	@ResponseBody
	public Map<String, Object> queryEmpByCondition(Integer deptId, Integer jobId, String empName, String pageNum,
			String pageSize, HttpServletRequest request, HttpServletResponse response) {

		int pageNumInt = 1;

		try {
			pageNumInt = Integer.parseInt(pageNum);
		} catch (Exception e) {

		}

		int pageSizeInt = 10;

		try {
			pageSizeInt = Integer.parseInt(pageSize);
		} catch (Exception e) {

		}

		// 返回满足条件的总记录数
		int recordCount = service.getEmpCountByCondition(deptId, jobId, empName);

		Pager pager = new Pager(recordCount, pageSizeInt, pageNumInt);

		// 返回满足条件的员工记录
		List<Emp> list = service.queryEmpByCondition(deptId, jobId, empName, pager);

		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	/**
	 * 全文检索员工
	 * @throws Exception 
	 */
	@RequestMapping("/emp/queryByIndex")
	@ResponseBody
	public EmpDto queryEmpByIndex(String keywords) throws Exception {
		
		return service.queryEmpByIndex(keywords);
	}	

}