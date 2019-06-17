package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用于封装order_detail表中的一行数据
 * @author RLDS
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = -569452398984381835L;
	/**
	 * 订单详情编号
	 */
	private Integer orderDetailId;
	/**
	 * 订单编号
	 */
	private Integer orderId;
	/**
	 * 商品编号
	 */
	private Integer goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 成交价
	 */
	private Double transactionPrice;
	/**
	 * 成交数量
	 */
	private Integer transactionCount;

}