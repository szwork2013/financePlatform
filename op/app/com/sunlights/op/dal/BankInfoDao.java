package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.BankInfoVo;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface BankInfoDao {

      public List<BankInfoVo> findBanks(PageVo pageVo);

      public void update(BankInfoVo bankInfoVo);

      public void save(BankInfoVo bankInfoVo);

//    public Activity add(Activity vo);
//
//    public List<Activity> findActicities();
//
//    public Activity modifyActivity(Activity vo);
//
//    public boolean deleteActivity(Long id);
//
//    public Activity findById(Long id);
//
//    public List<ActivityVo> findActivityWithRule(Long id, String title, String type);

}
