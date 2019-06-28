package com.springcloud.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@SuppressWarnings({ "rawtypes" })
	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public RedisTemplate redisTemplateInit() {
		this.redisTemplate.setKeySerializer(new StringRedisSerializer());
		this.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return this.redisTemplate;
	}
}
