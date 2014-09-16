package com.bx.notice.core.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bx.notice.util.ReadProperties;

public class SessionFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}


	/***
	 * 在发起业务请求前，获取登录用的信息
	 * 获取方式：获取方式：从平台中获取session，所有组件统一用平台的session
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String sessionMethod = ReadProperties.initProperties("/config.properties").getProperty("sessionmethod");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(sessionMethod == null || "".equals(sessionMethod)) {
			sessionMethod = "session";
		}
		
		UserinfoService us=new UserinfoService();
		boolean flag = us.getUserinfo(request,response);
		if(!flag) {
			return;
		}
		chain.doFilter(request, response);
		
	}

}
