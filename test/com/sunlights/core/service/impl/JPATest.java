//package com.sunlights.core.service.impl;
//
///**
// * <p>Project: financeplatform</p>
// * <p>Title: JPATest.java</p>
// * <p>Description: </p>
// * <p>Copyright (c) 2014 Sunlights.cc</p>
// * <p>All Rights Reserved.</p>
// *
// * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
// */
///*
// * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
// */
//
//import com.google.common.collect.ImmutableMap;
//import com.sunlights.DBUnitBasedTest;
//import com.sunlights.common.AppConst;
//import com.sunlights.customer.service.LoginService;
//import com.sunlights.customer.service.impl.LoginServiceImpl;
//import com.sunlights.customer.vo.CustomerFormVo;
//import org.dbunit.dataset.IDataSet;
//import org.junit.Test;
//import play.Logger;
//import play.db.jpa.JPA;
//import play.libs.F;
//import play.mvc.Result;
//import play.test.FakeRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.fest.assertions.Assertions.assertThat;
//import static play.test.Helpers.*;
//
//public class JPATest extends DBUnitBasedTest {
//
////    @Test
////    public void testRegisterService() {
////        JPA.withTransaction(new F.Callback0() {
////            public void invoke() {
////                CustomerFormVo customerFormVo = new CustomerFormVo();
////                customerFormVo.setMobilePhoneNo("13511599302");
////                customerFormVo.setPassWord("111111");
////                customerFormVo.setDeviceNo("deviceNo");
////
////                LoginService loginService = new LoginServiceImpl();
////                loginService.register(customerFormVo);
////            }
////        });
////    }
//
//        @Test
//        public void testRegister() throws Throwable{
//
//            final Map<String, String> formParams = new HashMap<>();
//            formParams.put("mobilePhoneNo", "13511598306");
//            formParams.put("deviceNo", "deviceNo");
//            formParams.put("type", "register");
//            formParams.put("passWord", "1");
//
//            Result result = getResult("/core/register", formParams);
//            Logger.info("result is " + contentAsString(result));
//            assertThat(status(result)).isEqualTo(OK);
//
//
////            needRollbackData("c_customer", "c_authentication");
//            IDataSet dataSet = getExpectedDataSet("registerExpected.xml");
//
//            assertDataSet(dataSet, "c_authentication", "password,mobile");
//            assertDataSet(dataSet, "c_customer", "login_password,mobile");
//
//        }
//
//}
