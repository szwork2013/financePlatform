package com.sunlights.op.dal.activity;

import models.ShareInfo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
public interface ActivityShareInfoDao {

    public List<ShareInfo> loadAll();

    public ShareInfo doInsert(ShareInfo shareInfo);

    public void doDelete(Long id);

    public ShareInfo doUpdate(ShareInfo shareInfo);

    public ShareInfo findById(Long id);

}
