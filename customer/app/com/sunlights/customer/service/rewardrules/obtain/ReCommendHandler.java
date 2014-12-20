package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.Customer;

/**
 * 关于推荐人的一些操作
 * 查询出推荐人，然后设置到请求里去
 * Created by tangweiqun on 2014/12/3.
 */
public class ReCommendHandler extends AbstractObtainRuleHandler {

    private CustomerDao customerDao = new CustomerDaoImpl();

    public ReCommendHandler() {

    }

    public ReCommendHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Customer customer = customerDao.findRecommenderInfo(requestVo.getCustId());
        if(customer == null) {
            return;
        } else {
            requestVo.setRecommendCustId(customer.getCustomerId());
            requestVo.set("recommendMobile", customer.getMobile());
        }
    }
}
