package com.sunlights;

import com.sunlights.account.vo.AcctChangeFlowVo;
import com.sunlights.common.utils.ConverterUtil;
import models.AcctChangFlow;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ConverterTest {
    
    @Test
    public void toEntity() throws Exception {
        AcctChangeFlowVo acctChangeFlowVo = new AcctChangeFlowVo();
        acctChangeFlowVo.setCustomerId("11111");
        acctChangeFlowVo.setAmount(new BigDecimal(20));
        acctChangeFlowVo.setPrdName("hello");

        AcctChangFlow acctChangFlow = ConverterUtil.toEntity(new AcctChangFlow(), acctChangeFlowVo);
        Assertions.assertThat(acctChangFlow.getAmount().intValue()).isEqualTo(20);
    }
}
