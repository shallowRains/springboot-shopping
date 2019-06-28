package com.springcloud.service;

import java.util.List;

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
	 * @return 成返回com.github.pagehelper.PageInfo<OrderDetail>实例
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);
	/**
	 * 向购物车中添加订单的明细信息
	 * @param userId 用户编号
	 * @param ordersDetail 订单明细
	 * @return 成功返回true失败返回false
	 */
	public abstract boolean addShopping(Integer userId,OrderDetail ordersDetail);
	/**
	 * 获得指定用户的购物车信息
	 * @param userId 用户id
	 * @return 成功返回java.util.List<OrderDetail>类型的实例，失败返回null
	 */
	public abstract List<OrderDetail> selectShopping(Integer userId);
	
	/**
	 * 删除指定用户购物车中的商品信息 
	 * @param userId 用户编号
	 * @param orderDetail 商品编号
	 * @return 成功返回true，失败返回false
	 */
	public abstract boolean removeShopping(Integer userId,OrderDetail orderDetail);

}
