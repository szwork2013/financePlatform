package com.sunlights.op.service.activity.impl;


import com.google.common.collect.Lists;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.ActivityShareInfoDao;
import com.sunlights.op.dal.activity.impl.ActivityShareInfoDaoImpl;
import com.sunlights.op.service.activity.ActivityShareInfoService;
import com.sunlights.op.vo.activity.ShareInfoVo;
import models.ShareInfo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
public class ActivityShareInfoServiceImpl implements ActivityShareInfoService {

    private ActivityShareInfoDao activityShareInfoDao = new ActivityShareInfoDaoImpl();

    @Override
    public List<ShareInfoVo> loadAll() {
        List<ShareInfo> shareInfos = activityShareInfoDao.loadAll();
        List<ShareInfoVo> shareInfoVos = Lists.newArrayList();
        for(ShareInfo shareInfo : shareInfos) {
            try {
                shareInfoVos.add(ConverterUtil.fromEntity(new ShareInfoVo(), shareInfo));
            } catch (Exception e) {

            }
        }

        return shareInfoVos;
    }

    @Override
    public ShareInfoVo add(ShareInfoVo shareInfoVo) {
        try {
            ShareInfo shareInfo = ConverterUtil.toEntity(new ShareInfo(), shareInfoVo);
            activityShareInfoDao.doInsert(shareInfo);
            shareInfoVo.setId(shareInfo.getId());
            shareInfoVo.setCreateTime(shareInfo.getCreateTime());
        } catch (Exception e) {

        }
        return shareInfoVo;
    }

    @Override
    public ShareInfoVo modify(ShareInfoVo shareInfoVo) {
        try {
            ShareInfo shareInfo = ConverterUtil.toEntity(new ShareInfo(), shareInfoVo);
            activityShareInfoDao.doUpdate(shareInfo);
            shareInfoVo.setUpdateTime(shareInfo.getUpdateTime());
        } catch (Exception e) {

        }
        return shareInfoVo;
    }

    @Override
    public void remove(Long id) {
        activityShareInfoDao.doDelete(id);
    }


}
