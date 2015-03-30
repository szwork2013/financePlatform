package com.sunlights.op.dal.statistics;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.statistics.CustomerInOutSummaryVo;
import com.sunlights.op.vo.statistics.PurchaseInfoVo;
import com.sunlights.op.vo.statistics.RecommenderInfoVo;

import java.util.List;

/**
 * Created by Administrator on 2015/1/14.
 */
public interface RecommenderDao {



    public List<RecommenderInfoVo> getRecommenderInfo();



    public List<PurchaseInfoVo> getCustomerPurchaseInfos(PageVo pageVo);
    public List<PurchaseInfoVo> getCustomerPurchaseItems(PageVo pageVo);


    List<CustomerInOutSummaryVo> CustomerInOutSummaryVos(PageVo pageVo);
}
