package com.icss.hr.job.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;


/**
 * 职务业务
 * @author DLETC
 *
 */
public class JobService {
	
	private JobDao dao = new JobDao();
	
	public void addJob(Job job)throws SQLException{
		dao.insert(job);
	}
	
	public void updateJob(Job job)throws SQLException{
		dao.update(job);
	}
	
	public void deleteJob(int jobId)throws SQLException{
		dao.delete(jobId);
	}
	
	public Job queryJobById(int jobId)throws SQLException{
		return dao.queryById(jobId);
	}
	
	public List<Job> quertyJob() throws SQLException{
		return dao.query();
	}
		
}