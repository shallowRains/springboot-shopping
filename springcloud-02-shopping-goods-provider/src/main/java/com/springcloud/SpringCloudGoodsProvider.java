package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.springcloud.dao") // 设置Mybatis接口的位置
public class SpringCloudGoodsProvider {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudGoodsProvider.class, args);
	}

}
