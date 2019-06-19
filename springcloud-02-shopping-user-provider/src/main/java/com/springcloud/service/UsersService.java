package com.springcloud.service;

import org.springframework.data.domain.Page;

import com.springcloud.entity.Users;

/**
 * 模型层的接口：用于定义用户模块的方法
 * 
 * @author RLDS
 *
 */
public interface UsersService {
	/**
	 * 验证用户是否登录成功
	 * 
	 * @param userId       用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId   权限编号
	 * @return 成功返回用户的完整信息，失败返回null
	 */
	public abstract Users login(Integer userId, String userPassword, Integer jdictionId);

	/**
	 * 添加新的用户信息
	 * 
	 * @param users 新的用户信息
	 * @return 添加成功返回com.springcloud.entity.Users类型的实例信息，否则返回null
	 */
	public abstract Users insert(Users users);

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param users      查新条件
	 * @param pageNumber 查询页数
	 * @return 成功返回org.springframework.data.domain.Page<Users>类型的数据，失败返回null
	 */
	public abstract Page<Users> select(Users users, Integer pageNumber);

	/**
	 * 修改指定用戶的状态
	 * 
	 * @param userId     用户id
	 * @param userStatus 用户状态
	 * @return 成功返回大于0的数，失败返回0
	 */
	public abstract Integer updateStatus(Integer userId, Integer userStatus);
	/**
	 * 查询指定编号的用户信息
	 * @param userId 用户编号
	 * @return 成功返回com.springcloud.entity.Users类型的实例，否则返回null
	 */
	public abstract Users selectById(Integer userId);
	/**
	 * 修改指定编号的用户信息
	 * @param users 用户信息
	 * @return 成功返回大于0的数，失败返回0
	 */
	public abstract Integer update(Users users);
	/**
	 * 根据用户编号修改用户信息
	 * @param users
	 * @return 成功返回大于0的数，失败返回0
	 */
	public abstract Integer updateMessage(Users users);
	/**
	 * 查询users表中指定用户名出现的次数
	 * @param userName 用户名
	 * @return 返回用户名在users表中出现的次数
	 */
	public abstract Long countByUserName(String userName);
	
	/**
	 * 用户登录方法
	 * @param users 用户信息
	 * @return
	 */
	public abstract Users userLogin(Users users);
}
