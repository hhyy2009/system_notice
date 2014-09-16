/**
 * 
 */
package com.bx.notice.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * JSON(JavaScript Object Natation)格式通用工具类
 * 
 * @author lzh
 * @createDate 2011-7-4 下午12:26:16
 * @since jdk1.6.0_26
 * @version 1.0
 */
public final class JsonUtilForDwz{
	
	public static void jsonForDwz(Map<String,Object> map) throws IOException{
		if(map!=null){
			HttpServletResponse response=(HttpServletResponse)map.get("response");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String result=map.get("result")+"";
			String msg=map.get("msg")+""; 
			String navTabId=map.get("navTabId")+"";
			String forwardUrl=map.get("forwardUrl")+"";
			String isClose=map.get("isClose")+"";
			String json=stringToJson(result,msg,navTabId,forwardUrl,isClose);
			out.print(json);
		}
	}

	/***
	 * 	操作处理之后，返回给DWZ框架的json数据格式
	 * @param result          处理结果
	 * @param msg		                提示信息
	 * @param navTabId        发生跳转时，跳转指向的panel的编号
	 * @param forwardUrl	     重定向的URL地址
	 * @return                符合JSON规范的字符串格式
	 */
	static String stringToJson(String result,String msg,String navTabId,String forwardUrl,String isClose){
		int len=result.length()+msg.length()+forwardUrl.length();
		final StringBuilder sb = new StringBuilder(len+500);
		
		String statusCode="";
		if(result==null || result.toLowerCase().equals("failure")){  //失败
			statusCode="300";
		}else if(result.toLowerCase().equals("success")){            //成功
			statusCode="200";
		}else if(result.toLowerCase().equals("timeout")){	         //超时
			statusCode="301";
		}
		
		String callbackType="";
		String t [] = isClose.split("@");
		isClose = t[0];
		//当是应用的时候不关闭当前navtab
		if(t.length==2&&"yy".equals(t[1])){
			callbackType="";
		}else{
			if(isClose!=null && isClose.equals("yes")){
				callbackType="closeCurrent";				 //关闭当前tab	
			}else{
				callbackType="forward";         			 //重新加载指定的url		
			}
		}
		sb.append('{');
		sb.append("\"statusCode\":\"").append(statusCode).append("\",");;
		sb.append("\"message\":\"").append(msg).append("\",");
		sb.append("\"navTabId\":\"").append(navTabId).append("\",");
		sb.append("\"rel\":\"").append("").append("\",");
		sb.append("\"callbackType\":\"").append(callbackType).append("\",");
		sb.append("\"forwardUrl\":\"").append(forwardUrl).append("\"");
		sb.append('}');
		return sb.toString();
	}
}
