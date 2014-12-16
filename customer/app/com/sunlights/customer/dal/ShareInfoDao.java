package com.sunlights.customer.dal;

import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/16.
 */
public interface ShareInfoDao {

    public ShareInfo getByType(String type);

}
