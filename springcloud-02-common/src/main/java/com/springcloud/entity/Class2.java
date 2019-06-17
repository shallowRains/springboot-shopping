package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * class2表对应的实体类
 * @author RLDS
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class2 implements java.io.Serializable{

	private static final long serialVersionUID = 13851492467804786L;
	/**
	 * 二级分类编号
	 */
	private Integer class2Id;
	/**
	 * 二级分类名称
	 */
	private String class2Name;
	/**
	 * 一级分类编号
	 */
	private Integer class1Id;	
	/**
	 * 备注信息
	 */
	private String remark;
}