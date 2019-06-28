package com.springcloud.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.springcloud.common.AlipayConfig;
import com.springcloud.entity.BizContent;
import com.springcloud.entity.SysServiceProvider;

@RestController
@RequestMapping("pay")
public class AlipayController {
	@RequestMapping(value = "alipay")
	public void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Float amount,String orderSubject,String orderNo)
			throws ServletException, IOException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "JSON", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type); // 获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl("http://127.0.0.1:8848/demo/updateOrdersStatus.html");
		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");// 在公共参数中设置回跳和通知地址
		//设置参数
		BizContent bizContent = new BizContent();
		bizContent.setOut_trade_no(orderNo);
		bizContent.setTotal_amount(amount);
		bizContent.setSubject(orderSubject);
		bizContent.setBody(orderSubject);
		bizContent.setProduct_code("FAST_INSTANT_TRADE_PAY");
		bizContent.setPassback_params("merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
		SysServiceProvider sysServiceProvider = new SysServiceProvider();
		sysServiceProvider.setSys_service_provider_id("2088511833207846");
		bizContent.setExtend_params(sysServiceProvider);
		String bizCt = JSON.toJSONString(bizContent);
		
//		alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"201502016070903601\","
//				+ "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":"+ amount +","
//				+ "    \"subject\":\""+ orderSubject +"\"," + "    \"body\":\"" + orderSubject + "\","
//				+ "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","
//				+ "    \"extend_params\":{" + "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");
// 填充业务参数
		alipayRequest.setBizContent(bizCt);
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
