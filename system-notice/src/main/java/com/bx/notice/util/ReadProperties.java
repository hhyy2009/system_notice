package com.bx.notice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadProperties {
	private static String sql_modulesms = "/sql-modulesms.properties";
	private static String smsSendControl = "/smsSendControl.properties";
	private static String hessian = "/hessian.properties";
	public static String getSmsSendControl() {
		return smsSendControl;
	}

	public static void setSmsSendControl(String smsSendControl) {
		ReadProperties.smsSendControl = smsSendControl;
	}

	private static Properties properties = null;
	private static InputStream in = null;
	private static Class<ReadProperties> c = ReadProperties.class;
	
	/**
	 * 初始化配置文件
	 * @param fileName
	 *            文件名
	 */
	public static Properties initProperties(String fileName) {
		if (null == properties) {
			properties = new Properties();
		}
		try {
			in = (InputStream) c.getResourceAsStream(fileName);
			properties.load(in);
			return properties;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String getProperty(String propertyFile,String param) {
		try {
			
			initProperties(propertyFile);
			return properties.getProperty(param);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public static String getValue(String param) {
		try {
			initProperties(hessian);
			return properties.getProperty(param);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
}
