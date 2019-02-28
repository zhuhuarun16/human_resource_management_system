package com.icss.hr.job.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.service.JobService;

/**
 * 根据职务id删除职务
 */
@WebServlet("/DelJobServlet")
public class DelJobServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得员工id
		String jobId = request.getParameter("jobId");
		
		//调用业务功能
		JobService service = new JobService();
		
		try {
			service.deleteJob(Integer.parseInt(jobId));		
		} catch (Exception e) {			
			e.printStackTrace();
		}		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}