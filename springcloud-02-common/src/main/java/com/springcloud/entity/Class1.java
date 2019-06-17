package com.springcloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * class1表对应的实体类
 * @author RLDS
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class1 implements java.io.Serializable{
	private static final long serialVersionUID = -5300706713029666878L;
	/**
	 * 一级分类编号
	 */
	private Integer class1Id;
	/**
	 * 一级分类名称
	 */
	private String class1Name;
	/**
	 * 一级分类序号
	 */
	private Integer class1Num;
	/**
	 * 备注信息
	 */
	private String remark;
}