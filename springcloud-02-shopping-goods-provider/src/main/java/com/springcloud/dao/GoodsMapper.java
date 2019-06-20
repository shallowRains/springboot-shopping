package com.springcloud.dao;

import com.springcloud.entity.Goods;
import java.util.List;

public interface GoodsMapper {
	int deleteByPrimaryKey(Integer goodsId);

	int insert(Goods record);

	Goods selectByPrimaryKey(Integer goodsId);

	List<Goods> selectAll();

	int updateByPrimaryKey(Goods record);

	/**
	 * 查询Goods表中满足条件的商品信息
	 * 
	 * @param goods 查询条件
	 * @return 成功返回java.util.List<Goods>类型的实例，否则返回null
	 */
	public abstract List<Goods> select(Goods goods);

	/**
	 * 根据条件修改Goods表中的知道编号的商品
	 * 
	 * @param goods 修改的商品信息
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer updateGoodsById(Goods goods);

	/**
	 * 查询销量前十的商品信息
	 * 
	 * @return 返回java.util.List<Goods>实例
	 */
	public abstract List<Goods> selectGroup();

	/**
	 * 查询热卖前五
	 * 
	 * @return 成功返回java.util.List<Goods>类型的实例
	 */
	public abstract List<Goods> selectGoodsHot();

	/**
	 * 查询新品前五
	 * 
	 * @return 成功返回java.util.List<Goods>类型的实例
	 */
	public abstract List<Goods> selectGoodsNew();
}