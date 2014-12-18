package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ShareInfoDao;
import models.ShareInfo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/16.
 */
public class ShareInfoDaoImpl extends EntityBaseDao implements ShareInfoDao {

    @Override
    public ShareInfo getByType(String type) {
        List<ShareInfo> shareInfos = findBy(ShareInfo.class, "shareType", type);
        if(shareInfos == null || shareInfos.isEmpty()) {
            return null;
        }
        return shareInfos.get(0);
    }
}
