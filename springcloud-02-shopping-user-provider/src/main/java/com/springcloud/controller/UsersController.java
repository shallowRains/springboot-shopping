package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 * 控制层：接受视图层的数据，并调用模型层相应的方法，将数据返回到视图层中
 * 
 * @author RLDS
 *
 */
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;

	/**
	 * 用户登录
	 * 
	 * @param userId       用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId   权限编号
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		ResultValue resultValue = new ResultValue();
		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			if (login != null) {
				resultValue.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				resultValue.setDataMap(map);
				return resultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("登录信息不正确，请重新输入！");
		return resultValue;
	}

	/**
	 * 添加用户
	 * 
	 * @param users 用户信息
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户录入成功！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("添加用户失败，请重试！");
		return rv;
	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			// 获得分页数据
			List<Users> list = page.getContent();
			// 判断是否查询到了数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("userList", list);
				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息添加到map中
				map.put("pageUtils", pageUtils);
				// 将Map添加到ResultValue对象中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStauts(@RequestParam("userId") Integer userId,
			@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if (status > 0) {
				rv.setCode(0);
				rv.setMessage("用户状态修改成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户状态更新失败！");
		return rv;
	}

	@RequestMapping(value = "/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			Users user = this.usersService.selectById(userId);
			if (user != null) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("user", user);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("查询用户信息失败！");
		return rv;
	}

	@RequestMapping(value = "/update")
	public ResultValue update(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer update = this.usersService.update(users);
			if (update > 0) {
				rv.setCode(0);
				rv.setMessage("用户信息修改成功！");
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！");
		return rv;
	}

	@RequestMapping(value = "/updateMessage")
	public ResultValue updateMessage(Users users) {
		ResultValue resultValue = new ResultValue();
		try {
			Integer message = this.usersService.updateMessage(users);
			if (message > 0) {
				resultValue.setCode(0);
				resultValue.setMessage("用户信息修改成功！");
				return resultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("用户信息修改失败！");
		return resultValue;

	}

	@RequestMapping(value = "/countByUserName")
	public ResultValue countByUserName(@RequestParam("userName") String userName) {
		ResultValue rv = new ResultValue();
		try {
			Long countByUserName = this.usersService.countByUserName(userName);

			rv.setCode(0);
			Map<String, Object> map = new HashMap<>();
			map.put("count", countByUserName);
			rv.setDataMap(map);
			return rv;

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("服务器正忙，请稍后再试！");
		return rv;
	}
	
	@RequestMapping(value = "/userLogin")
	public ResultValue userLogin(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users login = this.usersService.userLogin(users);
			if(login!=null) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("userMessage",login);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录失败！");
		return rv;
	}

}
