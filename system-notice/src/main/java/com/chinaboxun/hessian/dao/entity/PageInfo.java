package com.chinaboxun.hessian.dao.entity;

import java.io.Serializable;

/**
 * 分页信息类
 * @author 陆正烘
 * @CreateDate 2010-11-15 下午04:44:55
 * @since jdk1.6
 * @version 1.0
 *
 */
public class PageInfo implements Serializable {
	//当前页
	private int curPage = 1;
	//每页显示的记录数
	private int pageSize = 10;
	//总页数
	private int totalPage = 1;
	//总记录数
	private int totalCount = 0;
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	

}