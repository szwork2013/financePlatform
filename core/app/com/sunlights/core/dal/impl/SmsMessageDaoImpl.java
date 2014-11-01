package com.sunlights.core.dal.impl;

import com.sunlights.core.dal.SmsMessageDao;
import com.sunlights.core.models.SmsMessage;
import com.sunlights.common.dal.EntityBaseDao;
import org.springframework.stereotype.Service;

/**
 * <p>Project: fsp</p>
 * <p>Title: SmsMessageDapImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class SmsMessageDaoImpl extends EntityBaseDao implements SmsMessageDao {
    @Override
    public SmsMessage saveSmsMessage(SmsMessage smsMessage) {
        return create(smsMessage);
    }

    @Override
    public SmsMessage updateSmsMessage(SmsMessage smsMessage) {
        return update(smsMessage);
    }
}
