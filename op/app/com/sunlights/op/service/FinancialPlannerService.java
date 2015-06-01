package com.sunlights.op.service;

import com.google.inject.ImplementedBy;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.impl.FinancialPlannerServiceImpl;
import com.sunlights.op.vo.FinancialPlannerCustomerVo;
import com.sunlights.op.vo.FinancialPlannerVo;

import java.util.List;

/**
 * Created by Yuan on 2015/3/6.
 */
@ImplementedBy(FinancialPlannerServiceImpl.class)
public interface FinancialPlannerService {

    public List<FinancialPlannerVo> findFinancialPlannersBy(PageVo pageVo);

    public void save(FinancialPlannerVo financialPlannerVo);

    public List<FinancialPlannerCustomerVo> findPlannerCustomers(PageVo pageVo);
}
