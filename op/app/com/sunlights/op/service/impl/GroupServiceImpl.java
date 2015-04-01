package com.sunlights.op.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.GroupDao;
import com.sunlights.op.dal.impl.GroupDaoImpl;
import com.sunlights.op.service.GroupService;
import com.sunlights.op.vo.GroupVo;
import models.Customer;
import models.Group;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: GroupServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao = new GroupDaoImpl();

    @Override
    public List<GroupVo> findGroups(PageVo pageVo) {
        return groupDao.findGroups(pageVo);
    }

    @Override
    public Group createGroup(Group group) {
        group.setCreateTime(DBHelper.getCurrentTime());
        return groupDao.createGroup(group);
    }

    @Override
    public Group updateGroup(Group group) {
        group.setUpdateTime(DBHelper.getCurrentTime());
        return groupDao.updateGroup(group);
    }

    @Override
    public void deleteGroup(Long id) {
        boolean canDelete = groupDao.deleteValidate(id);
        if (!canDelete) {
            return;
        }
        groupDao.delete(id);
    }

    @Override
    public List<Customer> findCustomers(String sql, PageVo pageVo) {
        return groupDao.findCustomers(sql, pageVo);
    }

    @Override
    public void addCustomers(List<Customer> list, Long id) {
        List<Customer> notExistList = Lists.newArrayList();
        for (Customer customer : list) {
            boolean isExist = groupDao.findCustomerGroupIsExist(id, customer.getCustomerId());
            if (!isExist) {
                notExistList.add(customer);
            }

        }
        if (!notExistList.isEmpty()) {
            groupDao.addCustomers(list, id);
        }
    }

}
