package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;
import com.springcloud.vo.ResultValue;

@RestController
@RequestMapping("orderDetails")
public class OrderDetailsController {
	@Autowired
	private OrderDetailService orderDetailService;
	/**
	 * 通过订单编号查询订单详细信息
	 * @param orderId 订单编号
	 * @param pageNumber 页码
	 * @return
	 */
	@RequestMapping(value = "/selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue resultValue = new ResultValue();
		try {
			PageInfo<OrderDetail> selectByOrderId = this.orderDetailService.selectByOrderId(orderId,pageNumber);
			List<OrderDetail> list = selectByOrderId.getList();
			if(list != null && list.size() > 0) {
				resultValue.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("orderDetails", list);
				PageUtils pageUtils = new PageUtils(PageUtils.ORDER_DETAIL_COUNT,selectByOrderId.getPages() * PageUtils.ORDER_DETAIL_COUNT);
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息以指定的名字存入map集合中
				map.put("pageUtils", pageUtils);
				resultValue.setDataMap(map);
				return resultValue;
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		resultValue.setCode(1);
		resultValue.setMessage("订单详细信息获取失败");
		return resultValue;
	}
	/**
	 * 为指定编号的用户的购物车添加商品订单详细信息
	 * @param userId 用户编号
	 * @param orderDetail 订单明细
	 * @return
	 */
	@RequestMapping(value = "/addShopping")
	public ResultValue addShopping(@RequestParam("userId") Integer userId,OrderDetail orderDetail) {
		ResultValue rv = new ResultValue();
		try {
			boolean addShopping = this.orderDetailService.addShopping(userId, orderDetail);
			
			if(addShopping) {
				rv.setCode(0);
				rv.setMessage("购物车添加成功！");
				return rv;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("添加购物车失败！");
		return rv;
		
	}
	/**
	 * 获得指定编号的用户的购物车
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "/selectShopping")
	public ResultValue selectShopping(@RequestParam("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			List<OrderDetail> list = this.orderDetailService.selectShopping(userId);
			if(list != null) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("shoppingList", list);
				rv.setDataMap(map);
				return rv;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return rv;
	}
	/**
	 * 移除购物车
	 * @param userId
	 * @param orderDetail
	 * @return
	 */
	@RequestMapping(value = "/removeshopping")
	public ResultValue removeShopping(@RequestParam("userId") Integer userId,OrderDetail orderDetail) {
		ResultValue rv = new ResultValue();
		try {
			boolean removeShopping = this.orderDetailService.removeShopping(userId, orderDetail);
			if(removeShopping) {
				rv.setCode(0);
				return rv;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("移除购物车商品信息失败！");
		return rv;
	}
}
