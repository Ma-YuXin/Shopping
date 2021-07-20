package myx.ShoppingClient.Model;

/**
 * 分页基类
 * 
 * @Title: Pageable.java
 * @version V1.0 类用final命名，代表不能被修改
 */

//分页类
public final class Page extends Object implements java.io.Serializable {
	/**
	 * 
	 */

	/**
	 * 总记录数
	 */
	private int total = 0;

	/**
	 * 当前页，从1开始计数
	 */
	private int currentPage = 1;

	/**
	 * 分页数据
	 */

	/** 当前起始页 */
	private int startPage;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public Page(int total, int currentPage, int startPage, int endPage) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	/** 当前结束页 */
	private int endPage;

	public Page() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param pageSize    每页的记录数
	 * @param currentPage 当前页，从1开始计数
	 * @return
	 */

}