package com.icss.hr.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.job.dao.JobMapper;
import com.icss.hr.job.pojo.Job;

/**
 * Ö°Îñservice
 * @author DLETC
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class JobService {
	
	@Autowired
	private JobMapper mapper;
	
	public void addJob(Job job) {
		mapper.insert(job);
	}
	
	public void updateJob(Job job){
		mapper.update(job);
	}
	
	public void deleteJob(Integer jobId) {
		mapper.delete(jobId);
	}
	
	@Transactional(readOnly=true)
	public Job queryJobById(Integer jobId) {
		return mapper.queryById(jobId);
	}
	
	@Transactional(readOnly=true)
	public List<Job> queryJob() {
		return mapper.query();
	}

}