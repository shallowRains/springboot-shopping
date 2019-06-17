package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * goods表对应的实体类
 * @author RLDS
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements java.io.Serializable {

	private static final long serialVersionUID = 5550168279140722620L;
	/**
	 * 商品编号
	 */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private Double goodsPrices;
    /**
     * 商品折扣价格
     */
    private Double goodsDiscount;
    /**
     * 商品状态 
     */
    private Integer goodsStatus;
    /**
     * 商品数量
     */
    private Integer goodsCount;
    /**
     * 是否新品（0为新品，1为非新品）
     */
    private Integer goodsIsNew;
    /**
     * 是否热卖（0为热卖，1为非热卖）
     */
    private Integer goodsIsHot;
    /**
     * 商品级别（0为差，1为一般，2为优良）
     */
    private Integer goodsLevel;
    /**
     * 商品简介
     */
    private String goodsBrief;
    /**
     * 商品详情
     */
    private String goodsDetails;
    /**
     * 商品图片
     */
    private String goodsPhoto;
    /**
     * 分类2编号
     */
    private Integer class2Id;
    /**
     * 查询调价：商品价格下限
     */
    private Double goodsPriceMin;
    /**
     * 查询条件：商品价格上限
     */
    private Double goodsPriceMax;
    /**
     * 查询条件：一级分类编号
     */
    private Integer class1Id;
    /**
     * 商品销量：用于保存统计分组的结果
     */
    private Integer goodsSum;
    
}