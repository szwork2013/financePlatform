package com.sunlights.core.dal.impl;

import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.VersionRuleConfigDao;
import models.VersionRuleConfig;
import play.Configuration;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public class VersionRuleConfigDaoImpl extends EntityBaseDao implements VersionRuleConfigDao {

    private final static String MAX_SUPPORT_VERSION = "maxSupportVersion";
    private final static String MIN_SUPPORT_VERSION = "minSupportVersion";

    public VersionRuleConfig findByPlatform(String platform) {
        VersionRuleConfig versionRuleConfig = new VersionRuleConfig();
        versionRuleConfig.setChannel(platform);
        versionRuleConfig.setMaxSupportVersion(Configuration.root().getString(platform + "." + MAX_SUPPORT_VERSION));
        versionRuleConfig.setMinSupportVersion(Configuration.root().getString(platform + "." + MIN_SUPPORT_VERSION));
        return versionRuleConfig;
    }
}
