package com.sunlights.core.dal;

import models.VersionRuleConfig;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/12.
 */
public interface VersionRuleConfigDao {

    public VersionRuleConfig findByPlatform(String platform);


}
