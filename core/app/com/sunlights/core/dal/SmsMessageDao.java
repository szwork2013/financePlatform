package com.sunlights.core.dal;

import models.SmsMessage;

/**
 * <p>Project: fsp</p>
 * <p>Title: SmsMessageDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface SmsMessageDao {
  public SmsMessage saveSmsMessage(SmsMessage smsMessage);

  public SmsMessage updateSmsMessage(SmsMessage smsMessage);
}
