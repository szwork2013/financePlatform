package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.FeedBackDao;
import models.FeedBack;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FeedBackDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class FeedBackDaoImpl extends EntityBaseDao implements FeedBackDao{

    public FeedBack saveFeedBack(FeedBack feedBack){
        return create(feedBack);
    }
}
