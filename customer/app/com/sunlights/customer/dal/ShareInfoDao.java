package com.sunlights.customer.dal;

import models.ShareInfo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/16.
 */
public interface ShareInfoDao {

    public ShareInfo getParentByType(String type);

    public List<ShareInfo> getByParentId(Long parentId);


}
