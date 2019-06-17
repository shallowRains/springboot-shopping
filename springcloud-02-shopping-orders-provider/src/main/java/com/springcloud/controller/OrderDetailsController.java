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
}
