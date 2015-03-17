package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.dal.VersionRuleConfigDao;
import com.sunlights.core.dal.impl.VersionRuleConfigDaoImpl;
import com.sunlights.core.service.AppVersionService;
import com.sunlights.core.service.VersionRuleConfigService;
import models.VersionRuleConfig;
import org.apache.commons.lang3.StringUtils;
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

        if(versionRuleConfig == null) {
            Logger.error("没有配置响应平台的版本规则");
            return new Message(MsgCode.NOT_COFIG_VERSION_RULE, platform);
        } else {
            return versionMessage(platform, currentClientVersion, versionRuleConfig);
        }
    }

    private Message versionMessage(String platform, String currentClientVersion, VersionRuleConfig versionRuleConfig) {
        Message message;
        String latestVersion = appVersionService.getLatestVersionFromAppStore(platform);
        String compareVersion = latestVersion;
        /**
         * 如果user-agent里的版本号为空，则强制更新
         */
        if(StringUtils.isEmpty(currentClientVersion)) {
            return new Message(MsgCode.UPDATE_VERSION_TO_CURRENT, compareVersion);
        }

        if (currentClientVersion.compareTo(compareVersion) >= 0) {
            message = new Message(MsgCode.CURRENT_LATEST_VERSION, currentClientVersion);
        } else {
            String minSupportVersion = versionRuleConfig.getMinSupportVersion();
            if (currentClientVersion.compareTo(minSupportVersion) < 0) {
                message = new Message(MsgCode.UPDATE_VERSION_TO_CURRENT, compareVersion);
            } else if (currentClientVersion.compareTo(minSupportVersion) >= 0 && currentClientVersion.compareTo(compareVersion) < 0) {
                message = new Message(MsgCode.REMIND_UPDATE_VERSION, latestVersion);
            } else {
                message = new Message(MsgCode.NOT_COFIG_VERSION_RULE);
            }
        }
        return message;
    }


}
