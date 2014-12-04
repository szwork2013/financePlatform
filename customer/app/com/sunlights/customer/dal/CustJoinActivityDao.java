package com.sunlights.customer.dal;

import models.CustJoinActivity;

import java.util.List;

/**
 * Created by Administrator on 2014/12/3.
 */
public interface CustJoinActivityDao {

    public void doInsert(CustJoinActivity custJoinActivity);

    public List<CustJoinActivity> queryByCondition(CustJoinActivity custJoinActivity);

}
