package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.GroupVo;
import models.Customer;
import models.Group;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: GroupService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface GroupService {
    public List<GroupVo> findGroups(PageVo pageVo);

    public Group createGroup(Group group);

    public Group updateGroup(Group group);

    public void deleteGroup(Long id);

    public List<Customer> findCustomers(String sql, PageVo pageVo);

    public void addCustomers(List<Customer> list, Long id);
}
