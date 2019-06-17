package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Goods;

/**
 * 商品的模型层接口：用于定义商品模块的方法
 * 
 * @author RLDS
 *
 */
public interface GoodsService {
	/**
	 * 添加商品信息
	 * 
	 * @param goods 新的商品信息
	 * @return 成功返回大于0的整数,失败返回0
	 */
	public abstract Integer insert(Goods goods);

	/**
	 * 查询满足条件的商品信息
	 * 
	 * @param goods      查询条件
	 * @param pageNumber 页数
	 * @return 成功返回com.github.pagehelper.PageInfo<Goods>的实例，失败返回null
	 */
	public abstract PageInfo<Goods> select(Goods goods, Integer pageNumber);

	/**
	 * 根据条件修改Goods表中的知道编号的商品
	 * 
	 * @param goods 修改的商品信息
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer updateGoodsById(Goods goods);
	/**
	 * 更新商品的信息
	 * @param goods 商品信息
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer update(Goods goods);
	/**
	 * 查询销量前十的商品信息
	 * @return 返回java.util.List<Goods>实例
	 */
	public abstract List<Goods> selectGroup();
}
