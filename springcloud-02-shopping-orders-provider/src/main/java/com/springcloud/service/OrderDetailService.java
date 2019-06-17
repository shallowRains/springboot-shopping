package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;
/**
 * 订单明细模块模型层的接口，用于定义订单明细的方法
 * @author RLDS
 *
 */
public interface OrderDetailService {
	/**
	 * 根据订单编号查询商品的信息（分页功能）
	 * 
	 * @param orderId 订单编号
	 * @param pageNumber 页数
	 * @return 成返回java.util.List<OrderDetail>实例
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);
}
