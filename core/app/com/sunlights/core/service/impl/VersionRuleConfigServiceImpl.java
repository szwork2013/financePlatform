package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.dal.VersionRuleConfigDao;
import com.sunlights.core.dal.impl.VersionRuleConfigDaoImpl;
import com.sunlights.core.service.AppVersionService;
import com.sunlights.core.service.VersionRuleConfigService;
import models.VersionRuleConfig;
import play.Logger;

/**
 * Created by tangweiqun on 2015/3/12.
 *
 *
 */
public class VersionRuleConfigServiceImpl implements VersionRuleConfigService {

    private VersionRuleConfigDao versionRuleConfigDao = new VersionRuleConfigDaoImpl();

    private AppVersionService appVersionService = AppVersionServiceImpl.getInstance();

    @Override
    public Message checkVersion(String platform, String currentClientVersion) {
        CommonUtil.checkPlatform(platform);

        VersionRuleConfig versionRuleConfig = versionRuleConfigDao.findByPlatform(platform);

        Message message;

        if(versionRuleConfig == null) {
            Logger.error("没有配置响应平台的版本规则");
            message = new Message(MsgCode.NOT_COFIG_VERSION_RULE, platform);
            return message;
        }

        String latestVersion = appVersionService.getLatestVersionFromAppStore(platform);

        String tempLatest = "";

        if(versionRuleConfig.getMaxSupportVersion().compareTo(latestVersion) == 0) {
            tempLatest = versionRuleConfig.getMaxSupportVersion();
        } else if(versionRuleConfig.getMaxSupportVersion().compareTo(latestVersion) < 0) {
            tempLatest = latestVersion;
        }

        if(currentClientVersion.compareTo(tempLatest) == 0) {
            message = new Message(MsgCode.CURRENT_LATEST_VERSION, tempLatest);
        } else if(currentClientVersion.compareTo(versionRuleConfig.getMinSupportVersion()) < 0) {
            message = new Message(MsgCode.UPDATE_VERSION_TO_CURRENT, tempLatest);
        } else if(currentClientVersion.compareTo(versionRuleConfig.getMinSupportVersion()) >= 0 && currentClientVersion.compareTo(tempLatest) < 0){
            message = new Message(MsgCode.REMIND_UPDATE_VERSION, latestVersion);
        } else {
            message = new Message(MsgCode.NOT_COFIG_VERSION_RULE);
        }

        return message;
    }


}
