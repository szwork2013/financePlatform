package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.GroupDao;
import com.sunlights.op.vo.GroupVo;
import models.Customer;
import models.CustomerGroup;
import models.Group;
import models.MessagePushTxn;
import play.Logger;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: GroupDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class GroupDaoImpl extends EntityBaseDao implements GroupDao {
    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<GroupVo> findGroups(PageVo pageVo) {
        String sql = "select g.id,g.name,g.code,g.description,g.level,g.status,(select count(1) from c_customer_group cg where cg.group_id = g.id) " +
                " from c_group g" +
                " where 1 = 1" +
                " /~ and g.name like {name} ~/" +
                " /~ and g.status = {status} ~/" +
                " order by g.level desc";
        Query nativeQuery = createNativeQueryByMap(sql, pageVo.getFilter());
        int first = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        if (first > 0) {
            nativeQuery.setFirstResult(first);
        }
        if (pageSize > 0) {
            nativeQuery.setMaxResults(pageSize);
        }
        List list = nativeQuery.getResultList();
        String keys = "id,name,code,description,level,status,customerNum";
        List<GroupVo> groupVoList = ConverterUtil.convert(keys, list, GroupVo.class);

        String countSql = "select count(1) from Group ";
        Query countQuery = em.createQuery(countSql);
        pageVo.setCount(Integer.valueOf(countQuery.getSingleResult().toString()));

        return groupVoList;
    }

    @Override
    public Group createGroup(Group group) {
        return create(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return update(group);
    }

    @Override
    public void delete(Long id) {
        Group group = find(Group.class, id);
        delete(group);
    }

    public boolean findCustomerGroupIsExist(Long id, String customerId){
        String sql = "select count(1) from CustomerGroup cg where cg.groupId = :groupId and cg.customerId = :customerId";
        Query query = em.createQuery(sql);
        query.setParameter("groupId", id);
        query.setParameter("customerId", customerId);
        int count = Integer.valueOf(query.getSingleResult().toString());
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteValidate(Long id) {
        List<MessagePushTxn> messagePushTxnList = findBy(MessagePushTxn.class, "groupId", id);
        return messagePushTxnList.isEmpty() ? true : false;
    }

    @Override
    public List<Customer> findCustomers(String sql, PageVo pageVo) {
        if (sql == null || "".equals(sql.trim())) {
            sql = "select c.* from c_customer c";
        }
        Logger.debug(sql);
        Query query = em.createNativeQuery(sql, Customer.class);
        int first = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        if (first > 0) {
            query.setFirstResult(first);
        }
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }
        List list = query.getResultList();
        if (sql == null || !"".equals(sql.trim())) {
            sql = "select count(1) from c_customer where 1 = 1";
            pageVo.setCount(Integer.valueOf(em.createNativeQuery(sql).getSingleResult().toString()));
        }else{
            pageVo.setCount(list.size());
        }

        return list;
    }

    public void deleteCustomerByGroupId(Long id){
        String sql = "delete from c_customer_group where group_id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void addCustomers(List<Customer> list, Long id) {
        for (Customer customer : list) {
            CustomerGroup customerGroup = new CustomerGroup();
            customerGroup.setGroupId(id);
            customerGroup.setCustomerId(customer.getCustomerId());
            customerGroup.setCreateTime(DBHelper.getCurrentTime());

            create(customerGroup);
        }
    }
}
