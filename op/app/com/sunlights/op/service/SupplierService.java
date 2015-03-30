package com.sunlights.op.service;


import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.SupplierVo;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface SupplierService {

    // ======================= Yuan ===================//

    public List<SupplierVo> findSuppliersBy(PageVo pageVo);

    public void create(SupplierVo supplierVo);

    public void update(SupplierVo supplierVo);

    public void delete(SupplierVo supplierVo);

    // ======================= Yuan ===================//
}
