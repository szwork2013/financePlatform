package com.sunlights.core.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.PropertyFilter;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.AttentionService;
import com.sunlights.core.vo.AttentionVo;
import models.ProductAttention;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: ProductAttentionService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductAttentionService extends EntityBaseDao implements AttentionService {

    private PageService pageService = new PageService();

    @Override
    public void createAttentions(List<AttentionVo> attentionVos) {

        for (AttentionVo attentionVo : attentionVos) {
            ProductAttention productAttention = findAttentionBy(attentionVo);
            super.update(productAttention);
        }
    }

    private ProductAttention findAttentionBy(AttentionVo attentionVo) {
        List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
        PropertyFilter customerIdFilter = new PropertyFilter("EQS_customerId", attentionVo.getCustomerId());
        PropertyFilter productCodeFilter = new PropertyFilter("EQS_productCode", attentionVo.getCode());
        filters.add(customerIdFilter);
        filters.add(productCodeFilter);
        List<ProductAttention> productAttentions = super.find(ProductAttention.class, filters);
        return productAttentions.isEmpty() ? null : productAttentions.get(0);
    }

    @Override
    public void cancelAttention(AttentionVo attentionVo) {
        ProductAttention attention = findAttentionBy(attentionVo);
        super.delete(attention);
    }

    @Override
    public <X> List<X> findAttentions(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select new com.sunlights.core.vo.FundVo(f,pm)");
        jpql.append(" from FundNav f , ProductManage pm, ProductAttention pa");
        jpql.append(" where f.fundcode = pm.productCode");
        jpql.append(" and pm.upBeginTime < '" + currentDate + "'");
        jpql.append(" and pm.downEndTime >= '" + currentDate + "'");
        jpql.append(" and pm.productStatus = '" + DictConst.FP_PRODUCT_MANAGE_STATUS_1 + "'");
        jpql.append(" and f.fundcode = pa.productCode and pa.productType = '" + DictConst.FP_PRODUCT_TYPE_1 + "'");
        jpql.append(" /~ and pa.customerId = {customerId} ~/ ");
        jpql.append(" order by pm.productType,pm.recommendType,pa.createTime desc");
        return pageService.findXsqlBy(jpql.toString(), pageVo);
    }
}
