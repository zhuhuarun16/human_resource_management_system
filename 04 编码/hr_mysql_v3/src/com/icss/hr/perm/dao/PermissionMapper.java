package com.icss.hr.perm.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 权限dao
 * @author Administrator
 *
 */
public interface PermissionMapper {
	
	/**
	 * 查询用户权限列表
	 * @param empLoginName
	 * @return
	 */
	List<HashMap<String, Object>> queryPerm(@Param("empLoginName") String empLoginName);
	
	/**
	 * 查询用户角色列表
	 * @param empLoginName
	 * @return
	 */
	List<HashMap<String, Object>> queryRole(@Param("empLoginName") String empLoginName);
   
}