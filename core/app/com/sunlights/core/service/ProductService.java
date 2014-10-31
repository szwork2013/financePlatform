package com.sunlights.core.service;

import com.sunlights.core.vo.FundVo;
import com.sunlights.common.page.Pager;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductRecommendService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface ProductService {
    public List<FundVo> findProductRecommends(Pager page);
    public List<FundVo> findFunds(Pager page);
}
