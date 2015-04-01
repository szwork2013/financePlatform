package com.sunlights.op.service.activity.impl;


import com.google.common.collect.Lists;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.ActivityDao;
import com.sunlights.op.dal.activity.ActivityShareInfoDao;
import com.sunlights.op.dal.activity.impl.ActivityDaoImpl;
import com.sunlights.op.dal.activity.impl.ActivityShareInfoDaoImpl;
import com.sunlights.op.service.activity.ActivityShareInfoService;
import com.sunlights.op.vo.activity.ShareInfoVo;
import models.Activity;
import models.ShareInfo;


import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
public class ActivityShareInfoServiceImpl implements ActivityShareInfoService {

    private ActivityShareInfoDao activityShareInfoDao = new ActivityShareInfoDaoImpl();

    private ActivityDao activityDao = new ActivityDaoImpl();

    @Override
    public List<ShareInfoVo> loadAll() {
        List<ShareInfo> shareInfos = activityShareInfoDao.loadAll();
        List<ShareInfoVo> shareInfoVos = Lists.newArrayList();
        for(ShareInfo shareInfo : shareInfos) {
            if(shareInfo.getParentId() != null) {
                continue;
            }

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

            Activity activity = activityDao.findById(Long.valueOf(shareInfo.getRefId()));
            shareInfoVo.setRefStr(activity.getTitle());
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

            Activity activity = activityDao.findById(Long.valueOf(shareInfo.getRefId()));
            shareInfoVo.setRefStr(activity.getTitle());

        } catch (Exception e) {

        }
        return shareInfoVo;
    }

    @Override
    public void remove(Long id) {
        activityShareInfoDao.doDelete(id);
    }

    @Override
    public List<ShareInfoVo> getByParentId(String parentId) {
        List<ShareInfo> shareInfos = activityShareInfoDao.findByParentId(Long.valueOf(parentId));
        List<ShareInfoVo> shareInfoVos = Lists.newArrayList();
        for(ShareInfo shareInfo : shareInfos) {
            ShareInfoVo shareInfoVo = new ShareInfoVo();
            Activity activity = activityDao.findById(Long.valueOf(shareInfo.getRefId()));
            shareInfoVo.setRefStr(activity.getTitle());
            try {
                shareInfoVos.add(ConverterUtil.fromEntity(shareInfoVo, shareInfo));
            } catch (Exception e) {

            }
        }

        return shareInfoVos;
    }
}
