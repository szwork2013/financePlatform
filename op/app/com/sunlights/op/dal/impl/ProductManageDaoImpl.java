package com.sunlights.op.dal.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.ProductManageDao;
import com.sunlights.op.vo.ProductTypeVo;
import com.sunlights.op.vo.ProductVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/6.
 */
public class ProductManageDaoImpl extends EntityBaseDao implements ProductManageDao {


    @Override
    public List<ProductTypeVo> loadPrdTypeWithPrds() {
        StringBuilder sb = new StringBuilder();
        String keys = "type,typeName,code,name";
        String columns = " t.code, t.name, m.product_code, m.product_name ";
        sb.append("select ").append(columns)
                .append("from p_prd_type t  join p_product_manage m on t.code = m.product_type where t.code is not null");

        List<Object[]> resultRows = createLocalQuery(sb.toString()).getResultList();
        List<ProductVo> productVos = ConverterUtil.convert(keys, resultRows, ProductVo.class);
        Map<String, ProductTypeVo> productTypeVoMap = new HashMap<String, ProductTypeVo>();
        ProductTypeVo productTypeVo = null;
        for(ProductVo vo : productVos) {

            if(productTypeVoMap.containsKey(vo.getType())) {
                productTypeVoMap.get(vo.getType()).getProductVos().add(vo);
            } else {
                productTypeVo = new ProductTypeVo();
                productTypeVo.setPrdTypeCode(vo.getType());
                productTypeVo.setPrdTypeName(vo.getTypeName());
                productTypeVo.getProductVos().add(vo);
                productTypeVoMap.put(vo.getType(), productTypeVo);
            }
        }

        return new ArrayList<ProductTypeVo>(productTypeVoMap.values());
    }

    @Override
    public List<ProductVo> loadProducts(String prdType) {
        StringBuilder sb = new StringBuilder();
        String keys = "code,name";
        String columns = " t.product_code, t.product_name ";
        sb.append("select ").append(columns)
                .append("from p_product_manage t " );
        sb.append(" where 1 = 1 ");
        sb.append("  /~and t.product_type = {prdType}~/ ");
        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

        filterMap.put("EQS_prdType", prdType);
        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ProductVo> productVos = ConverterUtil.convert(keys, resultRows, ProductVo.class);
        return productVos;
    }

    @Override
    public List<ProductTypeVo> loadPrdTypes() {
        StringBuilder sb = new StringBuilder();
        String keys = "prdTypeCode,prdTypeName";
        String columns = " t.code, t.name ";
        sb.append("select ").append(columns)
                .append("from p_prd_type t   " );
        List<Object[]> resultRows = createLocalQuery(sb.toString()).getResultList();
        List<ProductTypeVo> productTypeVos = ConverterUtil.convert(keys, resultRows, ProductTypeVo.class);
        return productTypeVos;
    }
}
