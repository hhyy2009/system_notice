/**
 * 
 */
package com.bx.notice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期、时间处理通用类
 * 
 * @author 陆正烘
 * 
 */
public class SpecialDateTimeUtils {
	
	/**
	 * 获取指定格式的日期
	 * 
	 * @pattern 时间格式
	 * @return
	 * @throws MyException
	 */
	public static String getCurrentDate(String pattern) throws Exception {
		if (pattern == null) {
			throw new Exception("时间格式不正确，请检查!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date();
		String currentDate = sdf.format(date);
		return currentDate;
	}

	/**
	 * 当type为Calendar.YEAR时，该方法计算的是指定日期的前beforeN年，
	 * 当type为Calendar.MONTH时，该方法计算的是指定日期的前beforeN月，
	 * 当type为Calendar.DATE时，该方法计算的是指定日期的前beforeN天，
	 * 当type为Calendar.HOUR时，该方法计算的是指定日期的前beforeN小时
	 * @param specifiedDay 指定的日期
	 * @param beforeN
	 * @param type
	 * @param format
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int beforeN, int type, String format) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int mytype = c.get(type);
		c.set(type, mytype - beforeN);

		String dayBefore = new SimpleDateFormat(format).format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}
	
	/**
	 * 比较日期的大小，date1大于date2，返回1，date1小于date2，返回-1，date1等于date2，返回0
	 * @param date1
	 * @param date2
	 * @param format 格式
	 * @return
	 */
	public static int compareDate(String date1, String date2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			if(d1.getTime() > d2.getTime()) {
				return 1;
			}else if(d1.getTime() < d2.getTime()) {
				return -1;
			}else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat dd=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	} 
	
	public static String DateToString(Date date, String formatStr) {
		DateFormat dd=new SimpleDateFormat(formatStr);
		return dd.format(date);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SpecialDateTimeUtils.compareDate("2011-04-20", "2011-04-21", "yyyy-MM-dd"));
	}

}
