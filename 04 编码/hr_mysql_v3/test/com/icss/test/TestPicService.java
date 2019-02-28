package com.icss.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 测试图库service
 * @author DLETC
 *
 */
public class TestPicService {
	
	private ApplicationContext context;
	private PicService service;
	
	//@Before方法在任何@Test方法之前自动执行
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (PicService) context.getBean("picService");
	}
	
	@Test
	public void testInsert() throws IOException {
		
		File file = new File("e:\\1.jpg");		
		byte[] picData = FileCopyUtils.copyToByteArray(file);
		
		Pic pic = new Pic("2.jpg","吴亦凡2",file.length(),"jack",new Date(),picData);
		
		service.addPic(pic);		
	}
	
	@Test
	public void testDelete() throws IOException {
	
		service.deletePic(3);
		
	}
	
	@Test
	public void testQueryById() throws IOException {
	
		Pic pic = service.queryPicById(1);
		System.out.println(pic);
		
	}
	
	@Test
	public void testQuery() throws IOException {
	
		List<Pic> list = service.queryPic();
		
		for (Pic pic : list) {
			System.out.println(pic);
		}
		
	}

}