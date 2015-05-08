package com.sunlights.customer.dal;

import com.sunlights.common.vo.PageVo;
import models.RewardAccountDetails;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/19.
 */
public interface RewardAccountDetailsDao {

    public void doInsert(RewardAccountDetails rewardAccountDetails);

    public List<RewardAccountDetails> getByPage(PageVo pageVo);

}
