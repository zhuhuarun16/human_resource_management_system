package com.icss.hr.pic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.pic.dao.PicMapper;
import com.icss.hr.pic.pojo.Pic;

/**
 * Í¼¿âÒµÎñ
 * @author DLETC
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PicService {

	@Autowired
	private PicMapper mapper;
	
	public void addPic(Pic pic) {
		mapper.insert(pic);
	}
	
	public void deletePic(Integer picId) {
		mapper.delete(picId);
	}
	
	@Transactional(readOnly=true)
	public Pic queryPicById(Integer picId) {
		
		return mapper.queryById(picId);
	}
	
	@Transactional(readOnly=true)
	public List<Pic> queryPic() {
		
		return mapper.query();
	}
		
}