package com.sunlights.op.dal;

import com.sunlights.op.vo.ProductTypeVo;
import com.sunlights.op.vo.ProductVo;

import java.util.List;

/**
 * Created by Administrator on 2014/11/24.
 */
public interface ProductManageDao {

    public List<ProductTypeVo> loadPrdTypeWithPrds();

    public List<ProductVo> loadProducts(String prdType);

    public List<ProductTypeVo> loadPrdTypes();
}
