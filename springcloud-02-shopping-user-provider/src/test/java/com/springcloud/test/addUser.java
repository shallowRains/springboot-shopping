package com.springcloud.test;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class addUser {
	@Autowired
	private UsersService usersService;
	
	/**
	 * 添加用于测试的用户数据
	 */
	@Test
	public void add() {
		Random rnd = new Random();
		for(int i = 1;i<=50;i++) {
			Users user = new Users();
			user.setJdictionId(1);
			user.setUserAddress("贵州贵阳");
			user.setUserBirthday(new Date());
			user.setUserEmail("1782832653@qq.com");
			user.setUserName("User" + i);
			user.setUserNumber("122121212121212121");
			user.setUserPassword("123456"+i);
			user.setUserPhone("13800138000");
			user.setUserPhoto("http://127.0.0.1:8000/2.jpg");
			user.setUserSex(rnd.nextInt(2));
			user.setUserStatus(0);
			this.usersService.insert(user);
			
		}
	}
}
