package com.icss.hr.pic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 根据图片id返回图片二进制数据
 */
@WebServlet("/GetPicServlet")
public class GetPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//输出流
		OutputStream out = response.getOutputStream();

		//获得图片id
		String picId = request.getParameter("picId");
		
		//调用业务对象
		PicService service = new PicService();
		
		try {
			Pic pic = service.queryPicById( Integer.parseInt(picId) );
			
			//获得图片数据流
			InputStream is = pic.getPicData();
			
			//响应数据
			byte[] b = new byte[1024 * 8];
			
			int len = is.read(b);
			
			while (len != -1){
				out.write(b, 0, len);
				len = is.read(b);
			}
			
			is.close();
			out.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}