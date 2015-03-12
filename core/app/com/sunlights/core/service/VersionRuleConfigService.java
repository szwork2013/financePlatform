package com.sunlights.core.service;

import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.Message;
import models.VersionRuleConfig;

/**
 * Created by tangweiqun on 2015/3/12.
 */
public interface VersionRuleConfigService {
    /**
     * 根据平台(ios还是android)获取当前fp支持的前端app的版本的范围规则
     * 然后根据客户端的app的版本来核对客户端是否需要升级app版本
     *
     * @param platform  平台(ios还是android)
     * @param currentClientVersion  客户端的app版本
     * @return  核对客户app是否需要更新的信息
     */
    public Message checkVersion(String platform, String currentClientVersion);

}
