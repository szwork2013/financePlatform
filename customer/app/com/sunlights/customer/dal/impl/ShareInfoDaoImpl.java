package com.sunlights.customer.dal.impl;

import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ShareInfoDao;
import models.ShareInfo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/16.
 */
public class ShareInfoDaoImpl extends EntityBaseDao implements ShareInfoDao {


    @Override
    public ShareInfo getParentByType(String type) {
        List<ShareInfo> shareInfos = findBy(ShareInfo.class, "shareType", type);
        if (shareInfos == null || shareInfos.isEmpty()) {
            return null;
        }

        for(ShareInfo shareInfo : shareInfos) {
            Logger.debug("shareInfo == " + shareInfo + " shareInfo.getParentId() = " + shareInfo.getParentId());
            if(shareInfo.getParentId() == null) {
                return shareInfo;
            }
        }

        return null;
    }


    @Override
    public List<ShareInfo> getByParentId(Long parentId) {
        return findBy(ShareInfo.class, "parentId", parentId);
    }
}
