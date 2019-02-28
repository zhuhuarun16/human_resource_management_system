package com.icss.hr.job.dao;

import java.util.List;

import com.icss.hr.job.pojo.Job;

public interface JobMapper {
   
	void insert(Job job);
	
	void update(Job job);
	
	void delete(Integer jobId);
	
	Job queryById(Integer jobId);
	
	List<Job> query();
	
}