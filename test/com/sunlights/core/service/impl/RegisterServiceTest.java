package com.sunlights.core.service.impl;

import com.sunlights.DBUnitBasedTest;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.service.impl.LoginServiceImpl;
import com.sunlights.customer.vo.CustomerFormVo;
import org.dbunit.dataset.IDataSet;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;

/**
* <p>Project: financeplatform</p>
* <p>Title: RegisterServiceTest.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/
public class RegisterServiceTest extends DBUnitBasedTest {



    @Test
    public void registerTest() throws Throwable{

        JPA.withTransaction("test", false, new F.Function0<Object>() {
            @Override
            public Object apply() throws Throwable {
                CustomerFormVo customerFormVo = new CustomerFormVo();
                customerFormVo.setMobilePhoneNo("13511598305");
                customerFormVo.setPassWord("111111");
                customerFormVo.setDeviceNo("deviceNo");

                LoginService loginService = new LoginServiceImpl();
                loginService.register(customerFormVo);

                return null;
            }
        });

//        needRollbackData("c_customer", "c_authentication");
        IDataSet dataSet = getExpectedDataSet("registerExpected.xml");

        assertDataSet(dataSet, "c_authentication", "password,mobile");
        assertDataSet(dataSet, "c_customer", "login_password,mobile");

    }

    @Override
    public void rollback() {

    }


//    @Test
//    public void registerP2PTest() throws Throwable{
//
//        JPA.withTransaction("test", false, new F.Function0<Object>() {
//            @Override
//            public Object apply() throws Throwable {
//                CustomerFormVo customerFormVo = new CustomerFormVo();
//                customerFormVo.setMobilePhoneNo("15821948594");
//                customerFormVo.setPassWord("111111");
//                customerFormVo.setChannel("1");
//
//                LoginService loginService = new LoginServiceImpl();
//                loginService.register(customerFormVo);
//
//                return null;
//            }
//        });
//
//        needRollbackData("c_customer", "c_authentication");
//        IDataSet dataSet = getExpectedDataSet("registerExpected2.xml");
//
//        assertDataSet(dataSet, "c_authentication", "password,mobile,channel");
//        assertDataSet(dataSet, "c_customer", "login_password,mobile");
//
//    }
}
