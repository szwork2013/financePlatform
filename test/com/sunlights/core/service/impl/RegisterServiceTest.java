package com.sunlights.core.service.impl;

import com.sunlights.DBUnitBasedTest;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.service.impl.LoginServiceImpl;
import com.sunlights.customer.vo.CustomerFormVo;
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

    @Override
    public void rollback() {
        needRollbackData("c_customer,c_authentication,c_customer_session,c_customer_msg_setting,c_login_history,f_basic_account");
    }

    @Test
    public void testRegisterService(){
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                CustomerFormVo customerFormVo = new CustomerFormVo();
                customerFormVo.setMobilePhoneNo("13511598305");
                customerFormVo.setPassWord("111111");
                customerFormVo.setDeviceNo("deviceNo");

                LoginService loginService = new LoginServiceImpl();
                loginService.register(customerFormVo);
            }
        });
    }

}
