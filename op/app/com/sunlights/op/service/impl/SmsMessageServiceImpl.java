package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.SmsMessageDao;
import com.sunlights.op.dal.impl.SmsMessageDaoImpl;
import com.sunlights.op.service.SmsMessageService;
import com.sunlights.op.vo.SmsMessageVo;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SmsMessageServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsMessageServiceImpl implements SmsMessageService {
    private SmsMessageDao smsMessageDao = new SmsMessageDaoImpl();

    @Override
    public List<SmsMessageVo> findSmsMessageVos(PageVo pageVo) {
        return smsMessageDao.findSmsMessageVos(pageVo);
    }
}
