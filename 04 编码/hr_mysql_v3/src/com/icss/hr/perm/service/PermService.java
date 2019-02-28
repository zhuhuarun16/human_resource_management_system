package com.icss.hr.perm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.perm.dao.PermissionMapper;

/**
 * 权限业务层
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PermService {
	
	@Autowired
	private PermissionMapper mapper;
	
	@Transactional(readOnly=true)
	public List<HashMap<String, Object>> queryPerm(String empLoginName) {
		return mapper.queryPerm(empLoginName);
	}
	
	@Transactional(readOnly=true)
	public List<HashMap<String, Object>> queryRole(String empLoginName) {
		return mapper.queryRole(empLoginName);
	}

}