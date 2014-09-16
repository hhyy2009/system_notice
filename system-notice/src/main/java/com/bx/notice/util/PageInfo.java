/**
 * 
 */
package com.bx.notice.util;

import java.io.Serializable;

/**
 * 分页信息类
 * @author lzh
 * 
 */
public class PageInfo implements Serializable {
	// 当前页
	private int curPage = 1;
	//每页显示的记录数
	private int pageSize = 15;
	// 总页数
	private long totalPages = 1;
	// 总记录数
	private long totalCount = 0;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstResult() {
		return (getCurPage()-1)*this.pageSize;
	}
	public int getMaxresult() {
		return pageSize;
	}
	public long getTotalPages(){
		return (getTotalCount() + this.pageSize - 1)/ this.pageSize;
	}
}
