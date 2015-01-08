package com.sunlights.customer.service.msgcenter;

import com.google.common.collect.Lists;
import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.action.MsgCenterActionService;
import com.sunlights.customer.service.impl.CustomerService;
import models.Customer;
import models.CustomerMsgPushTxn;
import models.CustomerMsgSetting;
import models.MessageRuleMapping;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterActionServiceTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterActionServiceTest extends BaseTest {

    //methodName
    private final static String REGISTER = "register";
    private final static String LOGIN = "login";
    private final static String registerObtainReward = "registerObtainReward";

    //ruleCode
    private final static String SIGN_REMIND = "SIGN_REMIND";
    private final static String REGISTER_BEAN = "REGISTER_BEAN";

    private final static String mobilePhoneNo = "88888532175";

    private List<MessageRuleMapping> messageRuleMappingList = Lists.newArrayList();

    @Before
    public void prepare() {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                EntityManager em = JPA.em();
                String sql = "select mrm from MessageRuleMapping mrm where mrm.status = 'Y'";

                messageRuleMappingList = em.createQuery(sql, MessageRuleMapping.class).getResultList();
                if (messageRuleMappingList.isEmpty()) {
                    Logger.info(">>未配置消息规则映射表");
                    return;
                }
                Timestamp currentTime = DBHelper.getCurrentTime();
                Logger.info(mobilePhoneNo);

                CustomerService customerService = new CustomerService();
                Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
                String customerId = customer.getCustomerId();

                setAlias(em, currentTime, mobilePhoneNo, customerId);
            }
        });

    }

    @Test
    public void registerRemindTest() {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                EntityManager em = JPA.em();

                MsgCenterActionService msgCenterActionService = new MsgCenterActionService();
                CustomerService customerService = new CustomerService();
                Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
                String customerId = customer.getCustomerId();

                List<CustomerMsgPushTxn> customerMsgPushTxnList = Lists.newArrayList();

                List<String> params = Lists.newArrayList();

                for (MessageRuleMapping messageRuleMapping : messageRuleMappingList) {
                    String methodName = messageRuleMapping.getMethodName();
                    String messageType = messageRuleMapping.getMessageType();
                    String scene = messageRuleMapping.getScene();
                    String ruleCode = messageRuleMapping.getRuleCode();

                    if (REGISTER.equals(methodName) && SIGN_REMIND.equals(ruleCode)) {//注册提醒
                        Logger.info(">>注册提醒测试");
                        customerMsgPushTxnList = saveMessage(em, msgCenterActionService, customerId, messageType, scene, ruleCode, params);
                        assertThat(customerMsgPushTxnList.size()).isGreaterThan(0);
                    }
                }

                for (CustomerMsgPushTxn customerMsgPushTxn : customerMsgPushTxnList) {
                    em.remove(customerMsgPushTxn);
                }
            }
        });

    }


    @Test
    public void loginRemindTest() {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                EntityManager em = JPA.em();

                MsgCenterActionService msgCenterActionService = new MsgCenterActionService();
                CustomerService customerService = new CustomerService();
                Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
                String customerId = customer.getCustomerId();

                List<CustomerMsgPushTxn> customerMsgPushTxnList = Lists.newArrayList();

                List<String> params = Lists.newArrayList();

                for (MessageRuleMapping messageRuleMapping : messageRuleMappingList) {
                    String methodName = messageRuleMapping.getMethodName();
                    String messageType = messageRuleMapping.getMessageType();
                    String scene = messageRuleMapping.getScene();
                    String ruleCode = messageRuleMapping.getRuleCode();

                    if (LOGIN.equals(methodName) && SIGN_REMIND.equals(ruleCode)) {//登录提醒
                        Logger.info(">>登录提醒测试");
                        customerMsgPushTxnList = saveMessage(em, msgCenterActionService, customerId, messageType, scene, ruleCode, params);
                        assertThat(customerMsgPushTxnList.size()).isGreaterThan(0);
                    }
                }

                for (CustomerMsgPushTxn customerMsgPushTxn : customerMsgPushTxnList) {
                    em.remove(customerMsgPushTxn);
                }
            }
        });

    }


    @Test
    public void registerTest() {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                EntityManager em = JPA.em();

                MsgCenterActionService msgCenterActionService = new MsgCenterActionService();
                CustomerService customerService = new CustomerService();
                Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
                String customerId = customer.getCustomerId();

                List<CustomerMsgPushTxn> customerMsgPushTxnList = Lists.newArrayList();

                List<String> params = Lists.newArrayList();

                for (MessageRuleMapping messageRuleMapping : messageRuleMappingList) {
                    String methodName = messageRuleMapping.getMethodName();
                    String messageType = messageRuleMapping.getMessageType();
                    String scene = messageRuleMapping.getScene();
                    String ruleCode = messageRuleMapping.getRuleCode();

                    if (registerObtainReward.equals(methodName) && REGISTER_BEAN.equals(ruleCode)) {//注册活动
                        Logger.info(">>注册活动测试");
                        params.add("30");
                        customerMsgPushTxnList = saveMessage(em, msgCenterActionService, customerId, messageType, scene, ruleCode, params);
                        assertThat(customerMsgPushTxnList.size()).isGreaterThan(0);
                    }
                }

                for (CustomerMsgPushTxn customerMsgPushTxn : customerMsgPushTxnList) {
                    em.remove(customerMsgPushTxn);
                }
            }
        });

    }

    private void setAlias(EntityManager em, Timestamp currentTime, String mobilePhoneNo, String customerId) {
        Query query = em.createNamedQuery("findAliasByCustomerId");
        query.setParameter(1, customerId);
        List<String> alias = query.getResultList();
        if (alias.isEmpty()) {
            CustomerMsgSetting customerMsgSetting = new CustomerMsgSetting();
            customerMsgSetting.setCustomerId(customerId);
            customerMsgSetting.setPushOpenStatus(AppConst.STATUS_VALID);
            customerMsgSetting.setAlias(mobilePhoneNo);
            customerMsgSetting.setCreateTime(currentTime);
            em.persist(customerMsgSetting);
        }
    }

    private List<CustomerMsgPushTxn> saveMessage(EntityManager em, MsgCenterActionService msgCenterActionService, String customerId, String messageType, String scene, String ruleCode, List<String> params) {
        MessageHeaderVo messageActivityVo = new MessageHeaderVo();
        messageActivityVo.setMessageType(messageType);
        messageActivityVo.setScene(scene);
        messageActivityVo.setCustomerId(customerId);
        messageActivityVo.setParams(params);
        msgCenterActionService.operationRuleCode(messageActivityVo, ruleCode);

        String sql = "select c from CustomerMsgPushTxn c,MessageRule mr where c.customerId = :customerId and mr.code = :ruleCode";
        Query query = em.createQuery(sql, CustomerMsgPushTxn.class);
        query.setParameter("customerId", customerId);
        query.setParameter("ruleCode", ruleCode);
        List<CustomerMsgPushTxn> customerMsgPushTxnList = query.getResultList();
        Logger.info(customerMsgPushTxnList.size() + "");

        return customerMsgPushTxnList;
    }

}
