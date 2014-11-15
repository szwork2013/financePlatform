package com.sunlights;

import com.google.common.base.Splitter;
import com.sunlights.account.vo.AcctChangeFlowVo;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import models.AcctChangFlow;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @Test
    public void convertMap2Entity() throws Exception{
        String keys = "customerId,prdCode, prdName, amount";
        List values = new ArrayList();
        values.add("aaaa");
        values.add("bbbb");
        values.add("cccc");
        values.add(new BigDecimal(5));

        Map<String, Object> objectMap = ConverterUtil.createMap(Splitter.on(",").trimResults().splitToList(keys), values);
        AcctChangFlow acctChangFlow = new AcctChangFlow();
        ConverterUtil.convertMap2Entity(objectMap, acctChangFlow);

        Assertions.assertThat(acctChangFlow.getCustomerId()).isEqualTo("aaaa");
        Assertions.assertThat(acctChangFlow.getAmount().intValue()).isEqualTo(5);
    }



}
