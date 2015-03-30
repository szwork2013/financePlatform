package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.CustomerVo;
import models.MessageSmsTxn;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: CustomerService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface CustomerService {

	public List<CustomerVo> findCustomersBy(PageVo pageVo);

	public void unlock(Long customerId);

	public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn);
}
