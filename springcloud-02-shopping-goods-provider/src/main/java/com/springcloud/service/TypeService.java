package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
/**
 * 用于定义一级类别与二级类别的的查询
 * @author RLDS
 *
 */
public interface TypeService {
	/**
	 * 查询所有一级类别的信息
	 * @return 成功返回java.util.List类型实例，否则返回 null
	 */
	public abstract List<Class1> selectAllTypeOne();
	/**
	 * 根据一级类别的编号查询二级类别的信息
	 * @param typeOneId 一级类别编号
	 * @return 成功返回java.util.List类型实例，否则返回 null
	 */
	public abstract List<Class2> selectTypeTwoByTypeOneId(Integer typeOneId);
	/**
	 * 查询所有的二级类别的商品分类信息
	 * @return 成功返回java.util.List<Class2>类型的实例
	 */
	public abstract List<Class2> selectAllTypeTwo();
}
