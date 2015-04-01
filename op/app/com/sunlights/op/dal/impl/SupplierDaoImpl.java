package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.SupplierDao;
import models.Supplier;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/10/23.
 */
public class SupplierDaoImpl extends EntityBaseDao implements SupplierDao {
    @Override
    public List<Supplier> findAll() {
        return findAll(Supplier.class);
    }

    @Override
    public List<Supplier> findSubset(int start, int end) {
        return findAll(Supplier.class).subList(start, end);
    }

    @Override
    public Supplier findById(Long id) {
        List<Supplier> suppliers = findBy(Supplier.class, "id", id);
        if (suppliers.isEmpty()) {
            return null;
        } else {
            return suppliers.get(0);
        }
    }

    @Override
    public boolean doDelete(Long id) {
        Supplier supplier = findById(id);
        delete(supplier);
        return true;
    }

    @Override
    public boolean doInsert(Supplier supplier) {
        System.out.println(supplier.getWebsiteAddress());
        supplier.setCreateTime(new Date());
        supplier.setUpdateTime(new Date());
        create(supplier);
        return true;
    }

    @Override
    public boolean doUpdate(Supplier supplier) {
        System.out.println(supplier.getWebsiteAddress());
        Supplier t = findById(supplier.getId());
        supplier.setCreateTime(t.getCreateTime());
        supplier.setUpdateTime(new Date());
        update(supplier);
        return true;
    }
}
