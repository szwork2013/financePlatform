package com.sunlights.op.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.ProductRecommendDao;
import com.sunlights.op.vo.ProductRecommendVo;
import models.ProductRecommend;

import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: ProductRecommendDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductRecommendDaoImpl extends EntityBaseDao implements ProductRecommendDao {


    @Override
    public List<ProductRecommend> findProductRecommends() {
        return super.findAll(ProductRecommend.class, "priorityLevel", true);
    }

    @Override
    public void create(ProductRecommendVo productRecommendVo) {
        ProductRecommend productRecommend = productRecommendVo.getProductRecommend();
        super.create(productRecommend);

    }

    @Override
    public void update(ProductRecommendVo productRecommendVo) {
        ProductRecommend productRecommend = productRecommendVo.getProductRecommend();
        super.update(productRecommend);
    }

    @Override
    public void delete(ProductRecommendVo productRecommendVo) {
        ProductRecommend productRecommend = super.find(ProductRecommend.class, productRecommendVo.getId());
        super.delete(productRecommend);
    }

    @Override
    public ProductRecommend findProductRecommendById(Long id) {
        return super.find(ProductRecommend.class, id);
    }
}
