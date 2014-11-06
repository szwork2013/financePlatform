package com.sunlights.account.service;

import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.vo.PageVo;

import java.util.List;

/**
 * @author tangweiqun 2014/10/22
 */
public interface CapitalService {
  /**
   * 资产查询
   *
   * @param mobile
   * @param takeCapital4Prd 是否显示产品收益情况
   * @return
   */
  public TotalCapitalInfo getTotalCapital(String mobile, boolean takeCapital4Prd);

  /**
   * 产品收益情况
   *
   * @param mobile
   * @return
   */
  public List<Capital4Product> getAllCapital4Product(String mobile, PageVo pageVo);

  /**
   * 产品详情
   *
   * @param prdType
   * @param prdCode
   * @return
   */
  public HoldCapitalVo findCapitalProductDetail(String prdType, String prdCode);

  /**
   * 累计收益查询
   *
   * @return
   */
  public List<HoldCapitalVo> findTotalProfitList(CapitalFormVo capitalFormVo);


}
