package com.sunlights.op.dal;

import models.Supplier;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface SupplierDao {

    public List<Supplier> findAll();

    public  List<Supplier> findSubset(int start, int end);

    public Supplier findById(Long id);

    public boolean doDelete(Long id);

    public boolean doInsert(Supplier supplier);

    public boolean doUpdate(Supplier supplier);
}
