package com.sunlights.op.dal;

import models.Bank;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface BankDao {

    public boolean doInsert(Bank bank);

    public boolean doDelete(Long id);

    public boolean doUpdate(Bank bank);

    public List<Bank> findAll();

    public List<Bank> findSubset(int start, int end);

    public Bank findById(Long id);

    public int num();

    public boolean isExist(Bank b);

}
