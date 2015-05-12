package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.RewardAccountDetailsDao;
import models.RewardAccountDetails;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/19.
 */
public class RewardAccountDetailsDaoImpl extends EntityBaseDao implements RewardAccountDetailsDao {

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public void doInsert(RewardAccountDetails rewardAccountDetails) {
        rewardAccountDetails.setCreateTime(new Date());
        super.create(rewardAccountDetails);
    }

    @Override
    public List<RewardAccountDetails> getByPage(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select h from RewardAccountDetails h  ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and h.customerId = {customerId}~/ ");
        sb.append("  order by h.createTime desc ");

        return pageDao.findXsqlBy(sb.toString(), pageVo);
    }
}
