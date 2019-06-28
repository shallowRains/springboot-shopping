package com.springcloud.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块的控制层
 * 
 * @author RLDS
 *
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;

	/**
	 * 查询订单信息
	 * @param orders 订单信息
	 * @param pageNumber 页码
	 * @return
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue resultValue = new ResultValue();
		try {
			PageInfo<Orders> selectOrders = this.ordersService.selectOrders(orders, pageNumber);
			List<Orders> list = selectOrders.getList();
			if (list != null && list.size() > 0) {
				resultValue.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("ordersList", list);
				PageUtils pageUtils = new PageUtils(selectOrders.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息以指定的名字存入map集合中
				map.put("pageUtils", pageUtils);
				resultValue.setDataMap(map);
				return resultValue;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("订单信息查询失败！");
		return resultValue;
	}
	/**
	 * 更新订单状态
	 * @param orders 订单信息
	 * @return
	 */
	@RequestMapping(value = "/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		ResultValue resultValue = new ResultValue();
		try {
			Integer updateStatusByOrderId = this.ordersService.updateStatusByOrderId(orders);
			if(updateStatusByOrderId > 0) {
				resultValue.setCode(0);
				resultValue.setMessage("商品状态更新成功！");
				return resultValue;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("订单状态更新失败！");
		return resultValue;
	}
	@RequestMapping(value = "/selectGroup")
	public ResultValue selectGrop(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			List<Orders> list = this.ordersService.selectGroup(orders);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				for(Orders o :list) {
					x.add(o.getOrderMonth());
					y.add(o.getOrderPrice());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);
				return rv;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(0);
		return rv;
	}
	/**
	 * 添加订单与订单明细
	 * @param orders 视图层传递的json字符串
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(@RequestBody Orders orders) {
		orders.setOrderTime(new Date());
		ResultValue rv = new ResultValue();
		try {
			boolean insert = this.ordersService.insert(orders);
			if(insert) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("orderId", orders.getOrderId());
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("创建订单失败！");
		return rv;
	}
	/**
	 * 根据用户编号查询用户的订单信息
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "/selectByUserId")
	public ResultValue selectByUserId(@RequestParam("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			List<Orders> selectByUserId = this.ordersService.selectByUserId(userId);
			if(selectByUserId != null && selectByUserId.size() > 0) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("ordersList", selectByUserId);
				rv.setDataMap(map);
				return rv;
			}else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取订单信息失败！");
		return rv;
	}
}
