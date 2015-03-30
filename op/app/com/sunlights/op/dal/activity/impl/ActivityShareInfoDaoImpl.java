package com.sunlights.op.dal.activity.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.activity.ActivityShareInfoDao;
import models.ShareInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
public class ActivityShareInfoDaoImpl extends EntityBaseDao implements ActivityShareInfoDao {

    @Override
    public List<ShareInfo> loadAll() {
        return findAll(ShareInfo.class);
    }

    @Override
    public void doDelete(Long id) {
        ShareInfo old = findById(id);
        if(old == null) {
            return;
        }
        delete(old);
    }

    @Override
    public ShareInfo doInsert(ShareInfo shareInfo) {
        shareInfo.setCreateTime(new Date());
        super.create(shareInfo);
        return shareInfo;
    }

    @Override
    public ShareInfo doUpdate(ShareInfo shareInfo) {
        ShareInfo old = findById(shareInfo.getId());
        if(old == null) {
            return null;
        }
        shareInfo.setCreateTime(old.getCreateTime());
        shareInfo.setUpdateTime(new Date());
        update(shareInfo);

        return shareInfo;
    }

    @Override
    public ShareInfo findById(Long id) {
        List<ShareInfo> shareInfos = findBy(ShareInfo.class, "id", id);
        if(shareInfos == null || shareInfos.isEmpty()) {
            return null;
        }
        return shareInfos.get(0);
    }


}
