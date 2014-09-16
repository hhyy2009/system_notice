package com.bx.notice.core.web.filter;

/*
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.chinaboxun.hessian.dao.entity.UserSessionObject;

public class UserinfoService {
	
	
	UserSessionObject getUserinfo(HttpServletRequest request) {
		UserSessionObject userinfo = null;
		String sid = request.getParameter("sid");
		try {
			ServletContext context = request.getSession().getServletContext();
			ServletContext platformCxt = context
					.getContext("/boxun-platform-base");
			if (platformCxt != null && !platformCxt.equals("")) {
				Map<String, Object> userMap = (Map<String, Object>)platformCxt
						.getAttribute(sid);
				userinfo = this.combineUserData(userMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userinfo;
	}


	private UserSessionObject combineUserData(Map<String, Object> userMap) {
		UserSessionObject userinfo = null;
		if (userMap != null) {
			userinfo = new UserSessionObject();
			userinfo.setUserId(userMap.get("userId") != null ? userMap.get(
					"userId").toString() : null);
			userinfo.setUserName(userMap.get("userName") != null ? userMap.get(
					"userName").toString() : null);
			userinfo.setUserMobile(userMap.get("userMobile") != null ? userMap
					.get("userMobile").toString() : null);
			userinfo
					.setUserAccount(userMap.get("userAccount") != null ? userMap
							.get("userAccount").toString()
							: null);
			userinfo.setUserTel(userMap.get("userTel") != null ? userMap.get(
					"userTel").toString() : null);
			userinfo.setPosId(userMap.get("posId") != null ? userMap.get(
					"posId").toString() : null);
			userinfo.setUserVtype(userMap.get("userVtype") != null ? userMap
					.get("userVtype").toString() : null);
			userinfo.setUserType(userMap.get("userType") != null ? userMap.get(
					"userType").toString() : null);
			userinfo.setRightModel(userMap.get("rightModel") != null ? userMap
					.get("rightModel").toString() : null);
			userinfo.setUserSex(userMap.get("userSex") != null ? userMap.get(
					"userSex").toString() : null);
			userinfo.setUserBirth(userMap.get("userBirth") != null ? userMap
					.get("userBirth").toString() : null);
			userinfo.setUserEmail(userMap.get("userEmail") != null ? userMap
					.get("userEmail").toString() : null);
			userinfo.setUserAddr(userMap.get("userAddr") != null ? userMap.get(
					"userAddr").toString() : null);
			userinfo.setUserDeptId(userMap.get("userDeptId") != null ? userMap
					.get("userDeptId").toString() : null);
			userinfo
					.setUserDeptName(userMap.get("userDeptName") != null ? userMap
							.get("userDeptName").toString()
							: null);
			userinfo.setUserRoleId(userMap.get("userRoleId") != null ? userMap
					.get("userRoleId").toString() : null);
			userinfo
					.setUserRoleName(userMap.get("userRoleName") != null ? userMap
							.get("userRoleName").toString()
							: null);

			userinfo.setEcId(userMap.get("ecId") != null ? userMap.get("ecId")
					.toString() : null);
			userinfo.setEcName(userMap.get("ecName") != null ? userMap.get(
					"ecName").toString() : null);
			userinfo.setEcLogo(userMap.get("ecLogo") != null ? userMap.get(
					"ecLogo").toString() : null);
			userinfo.setEcTel(userMap.get("ecTel") != null ? userMap.get(
					"ecTel").toString() : null);
			userinfo.setEcFax(userMap.get("ecFax") != null ? userMap.get(
					"ecFax").toString() : null);
			userinfo
					.setEcLinkMobile(userMap.get("ecLinkMobile") != null ? userMap
							.get("ecLinkMobile").toString()
							: null);
			userinfo.setEcLinkMan(userMap.get("ecLinkMan") != null ? userMap
					.get("ecLinkMan").toString() : null);

			userinfo.setIndCode(userMap.get("indCode") != null ? userMap.get(
					"indCode").toString() : null);
			userinfo.setIndName(userMap.get("indName") != null ? userMap.get(
					"indName").toString() : null);
			userinfo.setAreaCode(userMap.get("areaCode") != null ? userMap.get(
					"areaCode").toString() : null);
			userinfo.setAreaName(userMap.get("areaName") != null ? userMap.get(
					"areaName").toString() : null);
			userinfo.setCustomNo(userMap.get("customNo") != null ? userMap.get(
					"customNo").toString() : null);
			userinfo.setBsAccessNo(userMap.get("bsAccessNo") != null ? userMap
					.get("bsAccessNo").toString() : null);
			userinfo.setBsFeeNo(userMap.get("bsFeeNo") != null ? userMap.get(
					"bsFeeNo").toString() : null);
			userinfo.setBsOtype(userMap.get("bsOtype") != null ? userMap.get(
					"bsOtype").toString() : null);
			userinfo.setBsCtype(userMap.get("bsCtype") != null ? userMap.get(
					"bsCtype").toString() : null);
			userinfo
					.setMenuMap(userMap.get("menuMap") != null ? (Map<String, String>) userMap
							.get("menuMap")
							: null);
			userinfo
					.setPriceMap(userMap.get("priceMap") != null ? (Map<String, String>) userMap
							.get("priceMap")
							: null);
		
		}
		return userinfo;
	}
}
*/

//-------------------------以下为新的UserinfoService----------------------------------------------
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinaboxun.hessian.dao.entity.UserSessionObject;

public class UserinfoService {
	
	/**
	 * 取得平台中的session，并把session中的值（Map）转换为UserSessionObject对象，在存入平台的session中
	 * @param request
	 * @param response
	 * @return true:平台的session正常（即没有invalidated），并且组件已经获得平台的session；false：平台session为空，或者已经invalidated，或者组件没有获得平台的session
	 */
	boolean getUserinfo(HttpServletRequest request,HttpServletResponse response) {
		UserSessionObject userinfo = null;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		String platformFullPath = "";
		try {
			String path = request.getContextPath();
			String platUserId = request.getParameter("platUserId");
			HttpSession session = request.getSession();
			ServletContext context = session.getServletContext();		
			if ((platUserId != null) && (platUserId.length() > 0)) {
		        session.setAttribute("platUserId", platUserId);
		      }
		      else if ((session.getAttribute("platUserId") != null) && (session.getAttribute("platUserId").toString().length() > 0)) {
		        platUserId = (String)session.getAttribute("platUserId");
		      }
			ServletContext platformCxt = context.getContext("/boxun-platform-base");
			platformFullPath = platformCxt.getInitParameter("platformFullPath");
			System.out.println(platformFullPath);
			if (platformCxt != null && !platformCxt.equals("")) {
				session = (HttpSession)platformCxt.getAttribute(platUserId);
				if(session != null) {
					Object obj = session.getAttribute("userMap");
					if(obj.getClass().getName().equals("java.util.HashMap")) {
						Map<String, Object> userMap = (Map<String, Object>)obj;
						userinfo = this.combineUserData(userMap);
						userinfo.setUserDepts((List<Map>)session.getAttribute("userDept"));
						userinfo.setMenuNoStr((String)session.getAttribute("menuNoStr"));
						userinfo.setMenuUrl((String)session.getAttribute("menuUrl"));
						session.setAttribute("userinfo", userinfo);
				        session.setAttribute("platUserId", platUserId);
					}
					System.out.println("【" + path + "】中的 sessionId = " + session.getId());
					return true;
				}
			}
			if (userinfo==null) {
				request.getSession().invalidate();
				response.sendRedirect(platformFullPath);
				return false;
			}
		} catch (Exception e) {
			if(e.getMessage().indexOf("Session already invalidated") != -1) {
				try {
					response.sendRedirect(platformFullPath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				return false;
			}
			e.printStackTrace();
		}
		return false;
	}


	private UserSessionObject combineUserData(Map<String, Object> userMap) {
		UserSessionObject userinfo = null;
		if (userMap != null) {
			userinfo = new UserSessionObject();
			userinfo.setUserId(userMap.get("userId") != null ? userMap.get(
					"userId").toString() : null);
			userinfo.setUserName(userMap.get("userName") != null ? userMap.get(
					"userName").toString() : null);
			userinfo.setUserMobile(userMap.get("userMobile") != null ? userMap
					.get("userMobile").toString() : null);
			userinfo
					.setUserAccount(userMap.get("userAccount") != null ? userMap
							.get("userAccount").toString()
							: null);
			userinfo.setUserTel(userMap.get("userTel") != null ? userMap.get(
					"userTel").toString() : null);
			userinfo.setPosId(userMap.get("posId") != null ? userMap.get(
					"posId").toString() : null);
			userinfo.setUserVtype(userMap.get("userVtype") != null ? userMap
					.get("userVtype").toString() : null);
			userinfo.setUserType(userMap.get("userType") != null ? userMap.get(
					"userType").toString() : null);
			userinfo.setRightModel(userMap.get("rightModel") != null ? userMap
					.get("rightModel").toString() : null);
			userinfo.setUserSex(userMap.get("userSex") != null ? userMap.get(
					"userSex").toString() : null);
			userinfo.setUserBirth(userMap.get("userBirth") != null ? userMap
					.get("userBirth").toString() : null);
			userinfo.setUserEmail(userMap.get("userEmail") != null ? userMap
					.get("userEmail").toString() : null);
			userinfo.setUserAddr(userMap.get("userAddr") != null ? userMap.get(
					"userAddr").toString() : null);
			userinfo.setUserDeptId(userMap.get("userDeptId") != null ? userMap
					.get("userDeptId").toString() : null);
			userinfo
					.setUserDeptName(userMap.get("userDeptName") != null ? userMap
							.get("userDeptName").toString()
							: null);
			userinfo.setUserRoleId(userMap.get("userRoleId") != null ? userMap
					.get("userRoleId").toString() : null);
			userinfo
					.setUserRoleName(userMap.get("userRoleName") != null ? userMap
							.get("userRoleName").toString()
							: null);

			userinfo.setEcId(userMap.get("ecId") != null ? userMap.get("ecId")
					.toString() : null);
			userinfo.setEcName(userMap.get("ecName") != null ? userMap.get(
					"ecName").toString() : null);
			userinfo.setEcLogo(userMap.get("ecLogo") != null ? userMap.get(
					"ecLogo").toString() : null);
			userinfo.setEcTel(userMap.get("ecTel") != null ? userMap.get(
					"ecTel").toString() : null);
			userinfo.setEcFax(userMap.get("ecFax") != null ? userMap.get(
					"ecFax").toString() : null);
			userinfo
					.setEcLinkMobile(userMap.get("ecLinkMobile") != null ? userMap
							.get("ecLinkMobile").toString()
							: null);
			userinfo.setEcLinkMan(userMap.get("ecLinkMan") != null ? userMap
					.get("ecLinkMan").toString() : null);

			userinfo.setIndCode(userMap.get("indCode") != null ? userMap.get(
					"indCode").toString() : null);
			userinfo.setIndName(userMap.get("indName") != null ? userMap.get(
					"indName").toString() : null);
			userinfo.setAreaCode(userMap.get("areaCode") != null ? userMap.get(
					"areaCode").toString() : null);
			userinfo.setAreaName(userMap.get("areaName") != null ? userMap.get(
					"areaName").toString() : null);
			userinfo.setCustomNo(userMap.get("customNo") != null ? userMap.get(
					"customNo").toString() : null);
			userinfo.setBsAccessNo(userMap.get("bsAccessNo") != null ? userMap
					.get("bsAccessNo").toString() : null);
			userinfo.setBsFeeNo(userMap.get("bsFeeNo") != null ? userMap.get(
					"bsFeeNo").toString() : null);
			userinfo.setBsOtype(userMap.get("bsOtype") != null ? userMap.get(
					"bsOtype").toString() : null);
			userinfo.setBsCtype(userMap.get("bsCtype") != null ? userMap.get(
					"bsCtype").toString() : null);
			userinfo
					.setMenuMap(userMap.get("menuMap") != null ? (Map<String, String>) userMap
							.get("menuMap")
							: null);
			
			userinfo
					.setPriceMap(userMap.get("priceMap") != null ? (Map<String, String>) userMap
							.get("priceMap")
							: null);
			//userinfo.setUserPwd(userMap.get("userPwd") !=null ? userMap.get("userPwd").toString() : null);
		}
		return userinfo;
	}
}