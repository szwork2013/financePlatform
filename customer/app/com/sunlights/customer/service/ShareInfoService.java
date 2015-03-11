package com.sunlights.customer.service;

import com.sunlights.customer.vo.ShareInfoContext;
import com.sunlights.customer.vo.ShareInfoVo;

/**
 * Created by tangweiqun on 2014/12/15.
 */
public interface ShareInfoService {

    public ShareInfoVo getShareInfoByType(ShareInfoContext context);

}
