package com.sunlights.core.service.impl;

import models.BankCard;

import org.junit.Test;

import play.db.jpa.JPA;
import play.libs.F;

import com.sunlights.DBUnitBasedTest;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.service.impl.BankCardServiceImpl;
import com.sunlights.customer.vo.BankCardVo;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: BankCardServiceTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class BankCardServiceTest extends DBUnitBasedTest{
    private String bank = "core/bank/";

    @Override
    public void rollback() {
        needRollbackData("c_customer,c_authentication,c_customer_session,f_basic_account,c_bank_card");
    }

    
    @Test
    public void testDeleteBankCard(){
        initData(bank + "bankDeleteInit.xml");

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                BankCardVo bankCardVo = new BankCardVo();

                bankCardVo.setBankName("中国银行");
                bankCardVo.setBankCard("365489744567456645415");
                bankCardVo.setBankSerial("002");

                BankCardService bankCardService  = new BankCardServiceImpl();
                BankCard bankCard = bankCardService.createBankCard("20141129170520010000000038", bankCardVo);


                bankCardService.deleteBankCard(bankCard.getId());

            }
        });
    }

}
