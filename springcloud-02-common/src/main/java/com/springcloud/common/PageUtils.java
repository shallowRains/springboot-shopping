package com.springcloud.common;

/**
 * 分页的工具类
 * 
 * @author A
 *
 */
public class PageUtils {

	/**
	 * 设置每页显示数据的行数
	 */
	public static final Integer PAGE_ROW_COUNT = 10;
	/**
	 * 设置每页显示的商品数量
	 */
	public static final Integer GOODS_ROW_COUNT = 8;
	/**
	 * 设置每页显示的订单详情的数量
	 */
	public static final Integer ORDER_DETAIL_COUNT = 5;
	/**
	 * 页码
	 */
	private Integer pageNumber;

	/**
	 * 起始行
	 */
	//private Integer startRow;

	/**
	 * 最后一页的页码
	 */
	private Integer lastPage;

	/**
	 * 分页按钮信息
	 */
	private int[] pageButton;

	private Integer pageRowCount;

	public PageUtils(Integer rowCount) {
		this(PAGE_ROW_COUNT, rowCount);
	}

	public PageUtils(Integer pageRowCount, Integer rowCount) {
		this.pageRowCount = pageRowCount;
		// 计算最后一页的页码
		this.lastPage = (rowCount + pageRowCount - 1) / pageRowCount;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber > this.lastPage) {
			this.pageNumber = this.lastPage;
			return;
		}
		this.pageNumber = pageNumber;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	/*public Integer getStartRow() {
		this.startRow = pageNumber * pageRowCount - pageRowCount;
		return startRow;
	}*/

	public Integer getLastPage() {
		return lastPage;
	}

	public int[] getPageButton() {
		// 分页按钮的起始按钮
		int startPage;
		// 分页按钮的结束按钮
		int endPage;

		if (this.lastPage <= 4) {
			// 如果最后一页小于4
			startPage = 1;
			endPage = this.lastPage;
		} else {
			if (this.pageNumber < 4) {
				// 如果当前页码小于4
				startPage = 1;
				endPage = 4;
			} else {
				startPage = this.pageNumber - 1;
				endPage = this.pageNumber + 2 <= this.lastPage ? this.pageNumber + 2 : this.lastPage;
				if ((endPage - startPage) <= 2) {
					startPage = endPage - 3;
				}
			}
		}
		this.pageButton = new int[endPage - (startPage - 1)];
		for (int i = 0; i < pageButton.length; i++) {
			pageButton[i] = startPage++;
		}
		return this.pageButton;
	}

}
