package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.BankDao;
import models.Bank;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * solving database related basic unit
 * CRUD in database
 * table: Bank
 * Created by Administrator on 2014/10/16.
 */

public class BankDaoImpl extends EntityBaseDao implements BankDao {
    /**
     *insert into database
     */
    @Override
    public  boolean  doInsert(Bank bank){
        bank.setStatus("Y");
        bank.setCreatedTime(new Date());
        create(bank);
        return true;
    }

    /**
     *delete from database
     */
    @Override
    public  boolean doDelete( Long id){
        Bank bank = findById(id);
        if ( bank==null){
            return false;
        }
        bank.setStatus("N");
        bank.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        update(bank);
        return true;
    }

    /**
     *update in database
     */
    @Override
    public  boolean doUpdate( Bank bank) {
        Bank bank1 = findById(bank.getId());
        if ( bank1==null){
            return false;
        }
        bank.setCreatedTime(bank1.getCreatedTime());
        bank.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        update(bank);
        return true;
    }

    @Override
    public List< Bank > findAll(){
        return findAll(Bank.class);
    }

    /**
     * in database
     * fetch a set of bank from database
     * @param end
     * @param start
     * @return banks<list>
     */
    @Override
    public  List< Bank > findSubset ( int start,int end ) {
        return findAll(Bank.class).subList(start, end);
    }

    /**
     * in database
     * select a piece of bank info via ID
     * @param id
     * @return bank<Bank>
     */
    @Override
    public  Bank  findById ( Long id ) {
        List<Bank> banks = findBy(Bank.class, "id", id);
        if ( banks.isEmpty() ) {
            return null;
        }else{
            return banks.get(0);
        }
    }

    /**
     * count the num of banks
     * @return <int>
     */
    @Override
    public int  num() {
        return findAll(Bank.class).size();
    }

    /**
     * in database
     * query to see it if exist
     * @param b <bank>
     * @return <boolean>
     */
    @Override
    public  boolean isExist( Bank b) {
        return findById(b.getId())!=null;
    }

}
