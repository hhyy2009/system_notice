/**
 * 
 */
package com.bx.notice.util;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chinaboxun.hessian.dao.entity.UserSessionObject;

/**
 * @author lzh
 *
 */
public class GetLoginUserInfo {
	/**
	 * 获得memcached中存入的登录用户相关信息，并组装成UserSessionObject对象
	 * @param platUserId 
	 * @return
	 */
	public static UserSessionObject getLoginSessionUserInfo(HttpServletRequest request, String platUserId) {
		String sessionMethod = ReadProperties.initProperties("/config.properties").getProperty("sessionmethod");
		if(sessionMethod == null || "".equals(sessionMethod)) {
			sessionMethod = "session";
		}
		String path = request.getContextPath();
		UserSessionObject userinfo = new UserSessionObject();
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		if (platUserId != null && platUserId.length() > 0) {
			session.setAttribute("platUserId", platUserId);
		} else {
			if (session.getAttribute("platUserId") != null
					&& session.getAttribute("platUserId").toString()
							.length() > 0) {
				platUserId = (String) session.getAttribute("platUserId");
			}
		}
		ServletContext platformCxt = context.getContext("/boxun-platform-base");
		if (platformCxt != null && !platformCxt.equals("")) {
			session = (HttpSession) platformCxt.getAttribute(platUserId);
			if (session != null) {
				session.setAttribute("platUserId",platUserId);
				userinfo = (UserSessionObject) session.getAttribute("userinfo");
			}
		}
		return userinfo;
	}
}
