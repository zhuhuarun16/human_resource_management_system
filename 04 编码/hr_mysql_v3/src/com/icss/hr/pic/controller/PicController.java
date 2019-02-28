package com.icss.hr.pic.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 图库控制器
 * @author DLETC
 *
 */
@Controller
public class PicController {

	@Autowired
	private PicService service;
	
	/**
	 * 添加图片
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/pic/add")
	public void add(String picInfo, @RequestParam("picData") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 获得登录用户名
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
				

		// pojo对象
		Pic pic = new Pic(file.getOriginalFilename(), picInfo, file.getSize(), empLoginName, new Date(), file.getBytes());

		service.addPic(pic);
	}

	/**
	 * 返回图片列表
	 */
	@RequestMapping("/pic/query")
	@ResponseBody
	public List<Pic> query(HttpServletRequest request, HttpServletResponse response) {

		return service.queryPic();

	}

	/**
	 * 返回图片二进制数据
	 */
	@RequestMapping("/pic/get")
	@ResponseBody
	public byte[] getPic(Integer picId, HttpServletRequest request, HttpServletResponse response) {

		Pic pic = service.queryPicById(picId);

		return pic.getPicData();
	}

	/**
	 * 删除图片
	 */
	@RequestMapping("/pic/delete")
	public void deletePic(Integer picId, HttpServletRequest request, HttpServletResponse response) {

		service.deletePic(picId);
	}
	
	/**
	 * 下载图片
	 * @throws IOException 
	 */
	@RequestMapping("/pic/download")
	public void downloadPic(Integer picId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 设置响应的MIME类型为二进制输出流
		response.setContentType("image/*");

		// 响应字节输出流
		OutputStream out = response.getOutputStream();

		//返回图片数据
		Pic pic = service.queryPicById(picId);
		
		//返回图片文件名
		String picName = new String(pic.getPicName().getBytes("utf-8"),"iso-8859-1");
		
		//设置响应报头，浏览器以附件形式接收数据
		response.setHeader("Content-disposition","attachment;filename=" + picName);
		
		//输出响应到客户端
		FileCopyUtils.copy(pic.getPicData(), out);
	}
	
}