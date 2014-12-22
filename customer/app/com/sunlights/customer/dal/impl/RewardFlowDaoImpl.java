package com.sunlights.customer.dal.impl;


import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.RewardFlowDao;
import models.RewardFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardFlowDaoImpl extends EntityBaseDao implements RewardFlowDao {

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public void saveRewardFlow(RewardFlow rewardFlow) {
        create(rewardFlow);
    }

    @Override
    public List<RewardFlow> findByCondition(RewardFlow rewardFlow, String startDate, String endDate, boolean isAsc) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from RewardFlow h where 1 =1")
                .append("/~  and h.custId  = {custId} ~/")
                .append("/~  and h.rewardType  = {rewardType} ~/")
                .append("/~  and h.scene  = {scene} ~/")
                .append("/~  and h.operatorType  = {operatorType} ~/")
                .append("/~  and h.activityType  = {activityType} ~/")
                .append("/~  and h.createTime  >= {startDate} ~/")
                .append("/~  and h.createTime  <= {endDate} ~/")
                .append(" order by h.createTime ");
        if(!isAsc) {
            jpql.append(" desc ");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", rewardFlow.getCustId());
        params.put("EQS_scene", rewardFlow.getScene());
        params.put("EQS_rewardType", rewardFlow.getRewardType());
        params.put("EQS_activityType", rewardFlow.getActivityType());
        params.put("EQI_operatorType", rewardFlow.getOperatorType());
        params.put("GED_startDate", startDate == null ? null : CommonUtil.stringToDate(startDate, CommonUtil.DATE_FORMAT_LONG));
        params.put("LED_endDate", endDate == null ? null : CommonUtil.stringToDate(endDate, CommonUtil.DATE_FORMAT_LONG));

        return findByMap(jpql.toString(), params);
    }

    @Override
    public RewardFlow findOneByCondition(RewardFlow rewardFlow, String startDate, String endDate) throws Exception {
        List<RewardFlow> rewardFlows = findByCondition(rewardFlow, startDate, endDate, true);
        if(rewardFlows == null || rewardFlows.isEmpty()) {
            return null;
        }
        return rewardFlows.get(0);
    }

    @Override
    public List<RewardFlow> findByCondition(RewardFlow rewardFlow) throws Exception {
        return findByCondition(rewardFlow, null, null, false);
    }

    @Override
    public List<RewardFlow> getMyFlowByPage(PageVo pageVo) {

        StringBuilder sb = new StringBuilder();
        sb.append("select h from RewardFlow h  ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and h.custId = {custId}~/ ");
        sb.append("  order by h.createTime desc ");

        return pageDao.findXsqlBy(sb.toString(), pageVo);
    }

    @Override
    public List<RewardFlow> getByType(String custId, String activityType, String rewardType) {
        StringBuilder sb = new StringBuilder();
        String keys = "activityId,title,content,shareUrl,iocnUrl";
        String columns = "  a.id, g.title, g.content, g.share_url,g.icon_url  ";
        sb.append("select ").append(columns)
                .append("from f_activity as a join f_activity_share_info as g on a.id=g.activity_id ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and a.scene = {scene}~/ ");


        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);


        filterMap.put("EQS_custId", custId);
        filterMap.put("EQS_activityType", activityType);
        filterMap.put("EQS_rewardType", rewardType);


        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<RewardFlow> rewardFlows = ConverterUtil.convert(keys, resultRows, RewardFlow.class);
        return rewardFlows;
    }
}
