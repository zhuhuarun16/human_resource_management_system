package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 根据部门id获得部门数据控制器
 */
@WebServlet("/GetDeptServlet")
public class GetDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获得部门id
		String deptId = request.getParameter("deptId");
		
		//调用业务对象
		DeptService service = new DeptService();
		
		try {			
			Dept dept = service.queryDeptById(Integer.parseInt(deptId));
			
			//在request范围中存储数据
			request.setAttribute("dept", dept);
			//转发到JSP视图
			request.getRequestDispatcher("/UpdateDept.jsp").forward(request, response);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}