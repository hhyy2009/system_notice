/**
 * 
 */
package com.bx.notice.util;

import java.util.List;

/**
 * 查询结果封装类
 * @author lzh
 *
 * @param <T> 实体类
 */
public class QueryResult<T> {
	/* 查询结果记录 */
	private List<T> resultList;
	/* 总记录 */
	private long totalrecord;
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
