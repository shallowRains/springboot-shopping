package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber) {

		PageHelper.startPage(pageNumber + 1, PageUtils.ORDER_DETAIL_COUNT);
		List<OrderDetail> selectByOrderId = this.orderDetailMapper.selectByOrderId(orderId);
		return new PageInfo<>(selectByOrderId);
	}

	@Override
	public boolean addShopping(Integer userId, OrderDetail ordersDetail) {
		try {
			@SuppressWarnings("unchecked")
			ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();
			// 创建redis数据库中保存数据的建
			String key = "user" + userId;
			// 向购物车添加数据
			// 获得指定建的list的查长度（获得此用户购物车中订单明细的数量）
			Long size = opsForList.size(key);
			if (size == 0) {
				// 当用户的购物车为空，直接将订单明细存入list即可
				opsForList.leftPush(key, ordersDetail);
			} else {
				// 当前用户的购物车不为空，需要判断购物车中是否存在新购买的订单明细
				// 获得redis中指定键的list中的所有数据
				List<OrderDetail> list = opsForList.range(key, 0, -1);
				// 在list中查找新的订单明细是否存在（在list集合中查询订单首次出现的位置，没有找到返回-1)
				int indexOf = list.indexOf(ordersDetail);
				if (indexOf == -1) {
					// 在购物车中没有找到新购买的订单明细，直接将新的订单明细添加到redis指定的list中即可
					opsForList.leftPush(key, ordersDetail);
				} else {
					// 在购物车中找到了新购买的订单明细
					// 获得redis中指定list的第N个元素
					OrderDetail o = opsForList.index(key, indexOf);
					Integer num1 = o.getTransactionCount();
					Integer num2 = ordersDetail.getTransactionCount();
					// 修改订单明细的购买数量
					o.setTransactionCount(num1 + num2);
					// 将修改后的订单明细重新放回到redis中指定键的list原来的位置
					opsForList.set(key, indexOf, o);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> selectShopping(Integer userId) {
		ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();
		return opsForList.range("user" + userId, 0, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeShopping(Integer userId, OrderDetail orderDetail) {
		ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();
		String key = "user" + userId;
		//获得redis中指定键值的list的长度
		Long size = opsForList.size(key);
		if(size == 0) {
			return false;
		}
		List<OrderDetail> list = opsForList.range(key, 0, -1);
		int indexOf = list.indexOf(orderDetail);
		
		//如果在购物车中没有找到指定的元素，结束方法
		if(indexOf == -1) {
			return false;
		}
		list.remove(indexOf);
		this.redisTemplate.delete(key);
		for(OrderDetail o : list) {
			opsForList.rightPush(key,o);
		}
		return true;
	}

}
