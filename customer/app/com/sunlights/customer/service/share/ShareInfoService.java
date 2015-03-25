package com.sunlights.customer.service.share;

import com.sunlights.customer.vo.ShareInfoVo;
import models.ShareInfo;

/**
 * Created by tangweiqun on 2014/12/15.
 */
public interface ShareInfoService {

    /**
     * 获取分享信息接口
     * 包括分享APP、分享活动、分享产品等信息
     *
     * @param context
     * @return  分享信息
     */
    public ShareInfoVo getShareInfoByType(ShareInfoContext context);

    /**
     * 根据类型和关联的id获取后台配置的分享信息
     * 1: 根据分享type获取分享信息，
     * 2: 如果分享信息与具体的id有关，再根据id获取具体的特殊化的分享信息
     * 3: 如果没有特殊性的分享信息，则返回第一条获取的分享信息
     *
     * @param type  分享种类
     * @param refId 关联id
     * @return  配置的分享信息
     */
    public ShareInfo getBasicShareInfoModel(String type, String refId);

}
