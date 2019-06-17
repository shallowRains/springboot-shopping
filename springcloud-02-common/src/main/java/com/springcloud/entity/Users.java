package com.springcloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Users表对应的实体类：用户封装Users表中的一行数据
 * @author RLDS
 *
 */
@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements java.io.Serializable{

	private static final long serialVersionUID = 2504015899170623862L;
	/**
	 * 用户id
	 */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	/**
	 * 用户名称
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 用户密码
	 */
	@Column(name = "user_password")
	private String userPassword;
	/**
	 * 用户身份证号
	 */
	@Column(name = "user_number")
	private String userNumber;
	/**
	 * 用户性别
	 */
	@Column(name = "user_sex")
	private Integer userSex;
	/**
	 * 用户手机号
	 */
	@Column(name = "user_phone")
	private String userPhone;
	/**
	 * 用户出生日期
	 */
	@Column(name = "user_birthday")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date userBirthday;
	/**
	 * 用户电子邮箱
	 */
	@Column(name = "user_email")
	private String userEmail;
	/**
	 * 用户头像
	 */
	@Column(name = "user_photo")
	private String userPhoto;
	/**
	 * 权限编号
	 */
	@Column(name = "jdiction_id")
	private Integer jdictionId;
	/**
	 * 用户状态 1禁用 0启用
	 */
	@Column(name = "user_status")
	private Integer userStatus;
	/**
	 * 用户地址
	 */
	@Column(name = "user_address")
	private String userAddress;
	
}
