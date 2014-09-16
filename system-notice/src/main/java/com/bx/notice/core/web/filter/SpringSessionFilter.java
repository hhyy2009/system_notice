package com.bx.notice.core.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.bx.notice.util.ReadProperties;

public class SpringSessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String sessionMethod = ReadProperties.initProperties("/config.properties").getProperty("sessionmethod");
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
