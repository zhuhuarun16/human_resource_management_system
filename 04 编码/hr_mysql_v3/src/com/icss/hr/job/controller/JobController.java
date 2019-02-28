package com.icss.hr.job.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * 职务控制器
 * @author DLETC
 *
 */
@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	
	/**
	 * 增加职务
	 * @param job
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/add")
	public void add(Job job,HttpServletRequest request,HttpServletResponse response) {
		service.addJob(job);		
	}
	
	/**
	 * 查询职务
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/job/query")
	public List<Job> query(HttpServletRequest request,HttpServletResponse response) {
		
		List<Job> list = service.queryJob();
		
		return list;
	}	
	
	/**
	 * 删除职务
	 * @param jobId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/delete")
	public void add(Integer jobId,HttpServletRequest request,HttpServletResponse response) {
		service.deleteJob(jobId);		
	}
	
	/**
	 * 查询单个职务
	 * @param jobId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/job/get")
	public Job query(Integer jobId,HttpServletRequest request,HttpServletResponse response) {
		Job job = service.queryJobById(jobId);
		return job;
	}	
		
	/**
	 * 修改职务
	 * @param job
	 * @param request
	 * @param response
	 */
	@RequestMapping("/job/update")
	public void update(Job job,HttpServletRequest request,HttpServletResponse response) {
		service.updateJob(job);		
	}
	
}