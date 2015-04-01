package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.SupplierService;
import com.sunlights.op.vo.SupplierVo;
import models.Supplier;

import java.util.Date;
import java.util.List;

/**
 * local service for Supplier
 * Created by Administrator on 2014/10/22.
 */
public class SupplierServiceImpl implements SupplierService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();


    // ======================= Yuan ===================//

    @Override
    public List<SupplierVo> findSuppliersBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new com.sunlights.op.vo.SupplierVo(s) from Supplier s");
        xsql.append(" where 1=1");
        xsql.append(" /~ and s.merchantName like {merchantName} ~/ ");
        xsql.append(" /~ and s.supplierCode like {supplierCode} ~/ ");
        xsql.append(" /~ and s.displayName like {displayName} ~/ ");
        xsql.append(" /~ and s.belongAddress like {belongAddress} ~/ ");
        xsql.append(" /~ and s.regAddress like {regAddress} ~/ ");

        List<SupplierVo> supplierVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return supplierVos;
    }

    @Override
    public void create(SupplierVo supplierVo) {
        if (hasSupplier(supplierVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.SUPPLIER_EXIST_ERROR));
        }
        Supplier supplier = supplierVo.convertToSupplier();
        supplier.setCreateTime(new Date());
        supplier.setUpdateTime(new Date());
		entityBaseDao.create(supplier);

    }

    @Override
    public void update(SupplierVo supplierVo) {
        if (hasSupplier(supplierVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.SUPPLIER_EXIST_ERROR));
        }
        Supplier supplier = supplierVo.convertToSupplier();
        supplier.setUpdateTime(new Date());
		entityBaseDao.update(supplier);
    }

    @Override
    public void delete(SupplierVo supplierVo) {
        Supplier supplier = entityBaseDao.find(Supplier.class, supplierVo.getId());
		entityBaseDao.delete(supplier);
    }

    private boolean hasSupplier(SupplierVo supplierVo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select s from Supplier s");
        jpql.append(" where s.supplierCode = '" + supplierVo.getSupplierCode().trim() + "'");
        if (supplierVo.getId() != null) {
            jpql.append(" and s.id <> ").append(supplierVo.getId());
        }
        List<Supplier> suppliers = entityBaseDao.find(jpql.toString());
        return !suppliers.isEmpty();
    }

    // ======================= Yuan ===================//
}
