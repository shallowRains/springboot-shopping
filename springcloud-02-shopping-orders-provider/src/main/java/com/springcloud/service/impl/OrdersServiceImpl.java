package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		// 在用户名两边添加百分号
		if (orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}

		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		List<Orders> selectOrders = this.ordersMapper.selectOrders(orders);
		return new PageInfo<>(selectOrders);
	}

	@Transactional
	@Override
	public Integer updateStatusByOrderId(Orders orders) {
		return this.ordersMapper.updateOrdersStatus(orders);
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGroup(orders);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean insert(Orders orders) {
		int orderId = this.ordersMapper.insert(orders);
		
		// 如果订单信息添加成功，才能添加订单明细信息
		if (orderId > 0) {
			Integer orderDetails = this.orderDetailMapper.insertOrderDetails(orders);
			if (orderDetails > 0) {
				// 将redis中指定用户的购物车信息删除
				String keys = "user" + orders.getUserId();
//				Boolean delete = this.redisTemplate.delete(keys);
//				if (delete) {
//					return true;
//				}
				ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();
				//获得用户购物车中的所有的信息
				List<OrderDetail> range = opsForList.range(keys, 0, -1);
				
				//获得需要结算的购物车信息
				List<OrderDetail> orderDetailsList = orders.getOrderDetailsList();
				if(range.size() == orderDetailsList.size()) {
					//当两个集合长度相等，表示用户对购物车中所有的商品进行了结算。
					Boolean delete = this.redisTemplate.delete(keys);
					if(delete) {
						return true;
					}
				}else {
					for (OrderDetail o : orderDetailsList) {
						int index = range.indexOf(o);
						if(index != -1) {
							range.remove(index);
						}
					}
					this.redisTemplate.delete(keys);
					for(OrderDetail o1:range) {
						opsForList.rightPush(keys, o1);
					}
					return true;
				}
				
				
			}
		}
		return false;
	}

	@Override
	public List<Orders> selectByUserId(Integer userId) {
		return this.ordersMapper.selectByUserId(userId);
	}

}
