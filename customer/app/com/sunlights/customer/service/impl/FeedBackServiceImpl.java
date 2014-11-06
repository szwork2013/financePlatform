package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.DBHelper;
import com.sunlights.customer.dal.FeedBackDao;
import com.sunlights.customer.dal.impl.FeedBackDaoImpl;
import com.sunlights.customer.service.FeedBackService;
import models.Customer;
import models.FeedBack;

import java.sql.Timestamp;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FeedBackService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class FeedBackServiceImpl implements FeedBackService {

  private CustomerService customerService = new CustomerService();
  private FeedBackDao feedBackDao = new FeedBackDaoImpl();

  public FeedBack saveFeedBack(String mobile, String content, String deviceNo) {
    Timestamp currentTime = DBHelper.getCurrentTime();

    FeedBack feedBack = new FeedBack();
    feedBack.setMobile(mobile);

    Customer customer = customerService.getCustomerByMobile(mobile);
    if (customer != null) {
      feedBack.setCustomerId(customer.getCustomerId());
    }
    feedBack.setDeviceNo(deviceNo);
    feedBack.setContext(content);

    feedBack.setCreateTime(currentTime);
    feedBack.setUpdateTime(currentTime);

    feedBackDao.saveFeedBack(feedBack);

    return feedBack;
  }

}
