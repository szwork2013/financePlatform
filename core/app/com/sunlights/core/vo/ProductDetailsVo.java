package com.sunlights.core.vo;

import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductDetailsVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductDetailsVo extends ProductVo {
    private int companyCode;//公司代码
    private String innerCode;//内部编号
    private String secuCode;//证券代码
    private String chiName;//中文名称
    private String chiNameAbbr;//中文名称缩写
    private String engName;//英文名称
    private String engNameAbbr;//英文名称缩写
    private String secuAbbr;//证券简称
    private String chiSpelling;//拼音证券简称
    private int secuMarket;//证券市场
    private int secuCategory;//证券类别
    private Date listedDate;//上市日期
    private int listedSector;//上市板块
    private int listedState;//上市状态
    private String ISIN;//ISIN代码
    private Date XGRQ;//更新时间
}
