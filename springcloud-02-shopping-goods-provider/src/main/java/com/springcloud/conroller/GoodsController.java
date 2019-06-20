package com.springcloud.conroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

@RestController
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer insert = this.goodsService.insert(goods);
			if (insert > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息录入成功！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息录入失败!");
		return rv;
	}

	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue resultValue = new ResultValue();
		try {
			// 查询满足条件的商品信息
			PageInfo<Goods> select = this.goodsService.select(goods, pageNumber);
			// 从分页信息中获得商品信息
			List<Goods> list = select.getList();

			if (list != null && list.size() > 0) {
				resultValue.setCode(0);

				Map<String, Object> map = new HashMap<>();
				// 将商品信息存入map集合中
				map.put("goodsList", list);
				PageUtils pageUtils = new PageUtils(8,select.getPages() * PageUtils.GOODS_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息以指定的名字存入map集合中
				map.put("pageUtils", pageUtils);

				resultValue.setDataMap(map);
				return resultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("没有找到满足条件的商品信息");
		return resultValue;
	}
	@RequestMapping(value = "/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue resultValue = new ResultValue();
		try {
			Integer updateGoodsById = this.goodsService.updateGoodsById(goods);
			if(updateGoodsById > 0) {
				resultValue.setCode(0);
				resultValue.setMessage("商品信息修改成功！");
				return resultValue;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("商品信息修改失败!");
		return resultValue;
	}
	@RequestMapping(value = "/update")
	public ResultValue update(Goods goods) {
		ResultValue resultValue = new ResultValue();
		try {
			Integer update = this.goodsService.update(goods);
			if(update > 0) {
				resultValue.setCode(0);
				resultValue.setMessage("商品信息修改成功！！！");
				return resultValue;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		resultValue.setMessage("商品信息修改失败！");
		return resultValue;
	}
	@RequestMapping("/selectGroup")
	public ResultValue selectGroup() {
		ResultValue resultValue = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() > 0) {
				resultValue.setCode(0);
				//创建两个集合，用于保存柱状图x轴与y轴的数据
				List<String> x = new ArrayList<>();
				List<Object> y = new ArrayList<>();
				for(Goods goods : list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x",x);
				map.put("y",y);
				resultValue.setDataMap(map);
				return resultValue;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		return resultValue;
	}
	/**
	 * 获得index页面的初始化商品信息（目前仅仅有商品热卖以及商品是否是新品两组信息）
	 * @return
	 */
	@RequestMapping(value = "/indexGoodsMessage")
	public ResultValue selectGoodsHot() {
		ResultValue resultValue = new ResultValue();
		try {
			List<Goods> selectGoodsHot = this.goodsService.selectGoodsHot();
			List<Goods> selectGoodsNew = this.goodsService.selectGoodsNew();
			if(selectGoodsHot != null && selectGoodsNew!=null && selectGoodsHot.size() > 0 && selectGoodsNew.size() > 0) {
				resultValue.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("goodsHotList", selectGoodsHot);
				map.put("goodsNewList", selectGoodsNew);
				resultValue.setDataMap(map);
				return resultValue;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		return resultValue;
	}
}
