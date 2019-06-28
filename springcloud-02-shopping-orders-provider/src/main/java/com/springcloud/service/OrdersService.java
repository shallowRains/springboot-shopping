package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Orders;

/**
 * 订单模块模型层的接口，用于定义订单模型方法
 * 
 * @author RLDS
 *
 */
public interface OrdersService {

	/**
	 * 查询满足条件的订单信息（分页功能）
	 * 
	 * @param orders 查询条件
	 * @return 成功返回com.github.pagehelper.PageInfo<Orders>类型的实例
	 */
	public abstract PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber);

	/**
	 * 根据ORDER_ID更新
	 * 
	 * @param orders 订单信息
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer updateStatusByOrderId(Orders orders);

	/**
	 * 查询指定年月区间的销售额
	 * 
	 * @param orders 查询条件
	 * @return 成功返回java.util.List<Orders>类型的实例
	 */
	public abstract List<Orders> selectGroup(Orders orders);

	/**
	 * 添加订单以及订单明细的数据
	 * 
	 * @param orders 订单与订单明细的信息
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean insert(Orders orders);

	/**
	 * 查询orders表中的指定用户编号的订单信息
	 * 
	 * @param userId 用户编号
	 * @return 成功返回java.util.List<Orders>类型的实例，失败返回null
	 */
	public abstract List<Orders> selectByUserId(Integer userId);
}
