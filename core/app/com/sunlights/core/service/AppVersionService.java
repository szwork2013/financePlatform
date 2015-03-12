package com.sunlights.core.service;

/**
 * Created by tangweiqun on 2015/3/12.
 */
public interface AppVersionService {
    /**
     * 根据平台类型获取该平台上金豆荚应用的最新版本
     *
     * @param platform  ios  android
     * @return  最新版本
     *
     */
    public String getLatestVersionFromAppStore(String platform);

    /**
     * 根据平台刷新内存的appStore中的最新版本的信息
     *
     * @param platform  平台(ios还是android)
     * @return  最新版本
     */
    public String refresh(String platform);

}
