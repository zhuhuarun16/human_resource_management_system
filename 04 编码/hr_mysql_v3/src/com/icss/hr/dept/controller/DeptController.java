package com.icss.hr.dept.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 部门控制器
 * @author DLETC
 *
 */
@Controller
public class DeptController {
	
	@Autowired
	private DeptService service;
	
	/**
	 * 增加部门
	 * @param dept 部门数据
	 * @param request 请求
	 * @param response 响应
	 * @return 转发地址
	 */
	@RequestMapping("/dept/add")
	public String add(Dept dept,HttpServletRequest request,HttpServletResponse response) {
		
		service.addDept(dept);
		
		//重定向到查询
		return "redirect:/dept/query";
	}
	
	/**
	 * 查询部门数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/query")
	public String query(Map map,HttpServletRequest request,HttpServletResponse response) {
		
		//调用业务对象
		List<Dept> list = service.queryDept();
		
		//在Map对象中存储数据
		map.put("list", list);//等价于request.setAttribute("list",list)
		
		return "QueryDept";
	}
	
	/**
	 * 删除部门
	 * @param deptId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/delete")
	public String delete(Integer deptId,HttpServletRequest request,HttpServletResponse response) {
		
		service.deleteDept(deptId);
		
		//重定向到查询
		return "redirect:/dept/query";
	}
	
	/**
	 * 查询单个部门
	 * @param deptId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/get")
	public ModelAndView get(Integer deptId,HttpServletRequest request,HttpServletResponse response) {
		
		Dept dept = service.queryDeptById(deptId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept",dept);
		mv.setViewName("UpdateDept");
		
		//转发到JSP视图
		return mv;
	}
	
	/**
	 * 修改部门
	 * @param dept
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dept/update")
	public String update(Dept dept,HttpServletRequest request,HttpServletResponse response) {
		
		service.updateDept(dept);
		
		//重定向到查询
		return "redirect:/dept/query";
	}
	
	/**
	 * 响应部门json数据集合
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dept/queryJson")
	public List<Dept> queryJson(HttpServletRequest request,HttpServletResponse response) {
		List<Dept> list = service.queryDept();
		return list;
	}
	
}