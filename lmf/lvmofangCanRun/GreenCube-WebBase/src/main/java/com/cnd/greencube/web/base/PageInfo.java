package com.cnd.greencube.web.base;

/**
 * 分页信息类
 * 
 * @author 胡晓光
 */
public class PageInfo {
	// 当前第几页
	private int currentPageIndex;
	// 总记录数
	private int rowCount;
	// 每页显示多少条
	private int pageSize;
	// 实际本页面所显示的行数（即本次查询结果集数目）
	private int showRows;
	// 是否重置当前页数
	private boolean resetPageIndex = false;

	public PageInfo() {

	}

	public int getCurrentPageIndex() {
		if (getResetPageIndex()) {
			return 1;
		}
		return this.currentPageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setCurrentPageIndex(int i) {
		this.currentPageIndex = i;
	}

	public void setPageSize(int i) {
		this.pageSize = i;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(int i) {
		this.rowCount = i;
	}

	public int getShowRows() {
		return this.showRows;
	}

	public void setShowRows(int i) {
		this.showRows = i;
	}

	boolean getResetPageIndex() {
		return this.resetPageIndex;
	}

	public void setResetPageIndex(boolean b) {
		this.resetPageIndex = b;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("pageSize=" + this.pageSize);
		sb.append("currentPageIndex=" + this.currentPageIndex);
		sb.append("rowCount=" + this.rowCount);
		sb.append("showRows=" + this.showRows);
		return sb.toString();
	}

	/**
	 * 得到第一个结果数目
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return new Integer((this.currentPageIndex - 1) * this.pageSize);
	}

	/**
	 * 得到最大结果数目
	 * 
	 * @return
	 */
	public int getMaxResults() {
//		return new Integer((this.currentPageIndex - 1) * this.pageSize
//				+ this.pageSize);
		return new Integer(this.pageSize);
	}
}
