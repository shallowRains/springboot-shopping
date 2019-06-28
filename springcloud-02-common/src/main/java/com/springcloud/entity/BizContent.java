package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizContent {
	private String out_trade_no;
	private String product_code;
	private Float total_amount;
	private String subject;
	private String body;
	private String passback_params;
	private SysServiceProvider extend_params;
}