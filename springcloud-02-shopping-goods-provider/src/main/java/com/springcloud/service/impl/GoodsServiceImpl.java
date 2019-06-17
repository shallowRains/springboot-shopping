package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.GoodsMapper;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	@Transactional
	public Integer insert(Goods goods) {
		return this.goodsMapper.insert(goods);
	}

	@Override
	public PageInfo<Goods> select(Goods goods, Integer pageNumber) {
		//在商品名称两边加上百分号
		goods.setGoodsName("%" + goods.getGoodsName() + "%");
		//设置每页的信息
		PageHelper.startPage(pageNumber + 1,PageUtils.GOODS_ROW_COUNT);
		//查询满足条件的商品信息
		List<Goods> list = this.goodsMapper.select(goods);
		//返回分页信息
		return new PageInfo<>(list);
	}
	
	@Override
	@Transactional
	public Integer updateGoodsById(Goods goods) {
		return this.goodsMapper.updateGoodsById(goods);
	}

	@Override
	@Transactional
	public Integer update(Goods goods) {
		return this.goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public List<Goods> selectGroup() {
		return this.goodsMapper.selectGroup();
	}
	


}
