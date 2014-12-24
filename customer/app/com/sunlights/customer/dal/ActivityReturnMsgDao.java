package com.sunlights.customer.dal;

import models.ActivityReturnMsg;

import java.util.List;

/**
 * Created by Administrator on 2014/12/23.
 */
public interface ActivityReturnMsgDao {

    public List<ActivityReturnMsg> getList(ActivityReturnMsg activityReturnMsg);


    public ActivityReturnMsg getByCondition(ActivityReturnMsg activityReturnMsg);

}
