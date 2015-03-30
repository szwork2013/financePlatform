package com.sunlights.op.dal;


import com.sunlights.op.vo.ProductRecommendVo;
import models.ProductRecommend;

import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: ProductRecommendDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface ProductRecommendDao {
    public List<ProductRecommend> findProductRecommends();

    public void create(ProductRecommendVo productRecommendVo);

    public void update(ProductRecommendVo productRecommendVo);

    public void delete(ProductRecommendVo productRecommendVo);

    public ProductRecommend findProductRecommendById(Long id);
}
