package com.icss.hr.pic.dao;

import java.util.List;

import com.icss.hr.pic.pojo.Pic;

/**
 * Í¼¿âdao
 * @author DLETC
 *
 */
public interface PicMapper {
	
	void insert(Pic pic);
	
	void delete(Integer picId);
	
	Pic queryById(Integer picId);
	
	List<Pic> query();

}
