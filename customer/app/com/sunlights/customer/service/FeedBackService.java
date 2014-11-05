package com.sunlights.customer.service;

import models.FeedBack;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FeedBackService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface FeedBackService {
    public FeedBack saveFeedBack(String mobile, String content, String deviceNo);
}
