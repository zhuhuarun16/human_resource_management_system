package com.icss.hr.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 公共过滤器
 */
public class CommonFilter implements Filter {

	public void destroy() {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		// 转换类型
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		// 设置允许其他域访问当前项目
		response.setHeader("Access-Control-Allow-Origin", "*");
				
		//输出请求url
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//web应用名称
		String app = request.getContextPath();
		
		//判断用户登录状态
		if ( !uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/logout.jsp")
				&& !uri.equals(app + "/emp/login")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/images") ) {
			
			//从session中获得用户号
			HttpSession session = request.getSession();
			String empLoginName = (String) session.getAttribute("empLoginName");
			System.out.println("empLoginName=" + empLoginName);
						
			if (empLoginName == null) {
				
				//判断当前请求是正常请求还是ajax请求
				String ajaxHeader = request.getHeader("x-requested-with");
				
				//如果是ajax请求，在响应的报头中设置一个特殊的标志，以便于前端做判断，
				//如果是正常请求，继续向下执行交给shiro处理
				if (ajaxHeader != null && ajaxHeader.equalsIgnoreCase("XMLHttpRequest")) {
					response.setHeader("sessionStatus", "timeout");
					return;
				} 							
				
			}			
			
		}	

		//继续向下执行
		chain.doFilter(request, response);		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}