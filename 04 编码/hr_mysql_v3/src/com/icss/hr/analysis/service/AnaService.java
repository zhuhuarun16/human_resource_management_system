package com.icss.hr.analysis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.analysis.dao.AnaMapper;

/**
 * Êý¾Ý·ÖÎöservice
 * @author DLETC
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AnaService {
	
	@Autowired
	private AnaMapper mapper;
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> queryEmpCount() {
		
		return mapper.queryEmpCount();
	}

}