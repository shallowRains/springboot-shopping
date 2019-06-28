package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.entity.Users;

/**
 * 持久化层： 定义对users表进行操作的方法
 * 
 * @author RLDS
 *
 */
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
	/**
	 * 判断用户是否登录成功
	 * 
	 * @param userId       用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId   权限编号
	 * @return 成功返回登录用户的完整信息，失败返回null
	 */
	@Query("select new com.springcloud.entity.Users(u.userId,u.userName,u.userPassword,u.userNumber,u.userSex,u.userPhone,u.userBirthday,u.userEmail,u.userPhoto,u.jdictionId,u.userStatus,u.userAddress) "
			+ "from com.springcloud.entity.Users u where u.userId=:userId and u.userPassword=:userPassword and u.jdictionId=:jdictionId and u.userStatus=0")
	public abstract Users login(@Param("userId") Integer userId, @Param("userPassword") String userPassword,
			@Param("jdictionId") Integer jdictionId);

	/**
	 * 修改users表中指定编号的用户状态
	 * 
	 * @param userStatus 用户状态
	 * @param userId     用户编号
	 * @return
	 */
	@Modifying
	@Query("update Users set userStatus=:userStatus where userId=:userId")
	public abstract Integer updateStatus(@Param("userStatus") Integer userStatus, @Param("userId") Integer userId);

	/**
	 * 修改指定user_id的用户信息
	 * 
	 * @param users 用户信息
	 * @return
	 */
	@Modifying
	@Query(value = "update Users u set u.userNumber=:#{#users.userNumber},u.userSex=:#{#users.userSex},u.userPhone=:#{#users.userPhone},u.userBirthday=:#{#users.userBirthday},u.userAddress=:#{#users.userAddress} where u.userId=:#{#users.userId}")
	public abstract Integer update(@Param("users") Users users);

	/**
	 * 根据user_id修改用户头像
	 * @param users
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userPhoto=:#{#users.userPhoto} where u.userId=:#{#users.userId}")
	public abstract Integer updateImage(@Param("users") Users users);
	/**
	 * 根据user_id修改用户密码
	 * @param users 用户信息
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userPassword=:#{#users.userPassword} where u.userId=:#{#users.userId}")
	public abstract Integer updatePassword(@Param("users") Users users);
	
	/**
	 * 根据user_id修改用户名称
	 * @param users 用户信息
	 * @return
	 */
	@Modifying
	@Query("update Users u set u.userName=:#{#users.userName} where u.userId=:#{#users.userId}")
	public abstract Integer updateName(@Param("users") Users users);
	
	/**
	 * 查询users表中指定用户名出现的次数
	 * @param userName 用户名
	 * @return 返回用户名在users表中出现的次数
	 */
	public abstract Long countByUserName(String userName);
	/**
	 * 用户登录方法
	 * @param userName 用户名
	 * @param userPassword 用户密码
	 * @param userStatus 用户状态
	 * @param jdictionId 用户权限
	 * @return 成功返回com.springcloud.entity.Users类型的实例
	 */
	public abstract Users findByUserNameAndUserPasswordAndUserStatusAndJdictionId(String userName,String userPassword,Integer userStatus,Integer jdictionId);

}
