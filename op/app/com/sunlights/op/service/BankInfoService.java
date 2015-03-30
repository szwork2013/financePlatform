package com.sunlights.op.service;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.BankInfoVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public interface BankInfoService {

//    public Activity add(ActivityVo vo);
//    public List<ActivityVo> findActicities();
//    public Activity modifyActivity(ActivityVo vo);
//    public void deleteActivity(Long id);
//    public List<ActivityVo> findActivityWithRule(Long id, String title, String type);

    /**
     * 查询银行信息
     * @return
     */
      public List<BankInfoVo> findBanks(PageVo pageVo);

    /**
     * 更新银行信息
     * @param bankInfoVo
     */
      public void update(BankInfoVo bankInfoVo);

    /**
     * 保存银行信息
     * @param bankInfoVo
     */
      public void save(BankInfoVo bankInfoVo);
}
