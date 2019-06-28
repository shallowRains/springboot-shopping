package com.springcloud.dao;

import com.springcloud.entity.Orders;
import java.util.List;

public interface OrdersMapper {
	int deleteByPrimaryKey(Integer orderId);

	int insert(Orders record);

	Orders selectByPrimaryKey(Integer orderId);

	List<Orders> selectAll();

	int updateByPrimaryKey(Orders record);

	/**
	 * 查询orders表中满足条件的订单信息
	 * 
	 * @param orders 查询条件
	 * @return 成功返回java.util.List<Orders>实例，否则返回空
	 */
	public abstract List<Orders> selectOrders(Orders orders);

	/**
	 * 修改ORDERs表中指定ORDER_ID的订单状态
	 * 
	 * @param orders 订单信息
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer updateOrdersStatus(Orders orders);

	/**
	 * 查询指定日期范围的销售额
	 * 
	 * @param orders 查询条件
	 * @return 成功返回java.util.List<Orders>实例
	 */
	public abstract List<Orders> selectGroup(Orders orders);

	/**
	 * 查询orders表中的指定用户编号的订单信息
	 * 
	 * @param userId 用户编号
	 * @return 成功返回java.util.List<Orders>类型的实例，失败返回null
	 */
	public abstract List<Orders> selectByUserId(Integer userId);

}