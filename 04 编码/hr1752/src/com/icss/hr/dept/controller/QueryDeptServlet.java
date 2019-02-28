package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 查询部门控制器
 */
@WebServlet("/QueryDeptServlet")
public class QueryDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//调用业务对象
		DeptService service = new DeptService();
		
		try {
			List<Dept> list = service.queryDept();
			
			//把数据存储在request范围
			request.setAttribute("list", list);
			
			//请求转发到JSP视图
			request.getRequestDispatcher("/QueryDept.jsp").forward(request, response);
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
			//手动转发到错误提示页
			request.getRequestDispatcher("/error.html").forward(request, response);
		}		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}