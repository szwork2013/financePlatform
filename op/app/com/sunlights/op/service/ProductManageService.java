package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.CodeVo;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.ProductManageVo;
import com.sunlights.op.vo.ProductTypeVo;

import java.util.List;

/**
 * Created by Administrator on 2014/10/30.
 */
public interface ProductManageService {


    //==========================================Yuan==========================================//

    public List<ProductManageVo> findProductManagesBy(PageVo pageVo);

    public void create(ProductManageVo productManageVo);

    public void update(ProductManageVo productManageVo);

    public void delete(ProductManageVo productManageVo);

    public List<String> findSynchronousProductCodes();

    public List<CodeVo> findCodes(PageVo pageVo);

    public void grabProfitHistoryByCode(String code);
    public void grabAllProfitHistory();


    //==========================================Yuan==========================================//

    public List<ProductTypeVo> loadPrdType();

    public List<KeyValueVo> loadProducts(String prdType);

    public List<KeyValueVo> loadPrdTypes();

    /**
     * 产品刷新缓存
     */
    public void refreshProduct();
}
