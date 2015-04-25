package com.sunlights.op.dal.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import models.ExchangeResult;
import models.HoldReward;
import models.RewardFlow;

import java.util.List;

/**
 * Created by Administrator on 2014/12/11.
 */
public interface ExchangeResultDao {

	public ExchangeResult findById(Long id);
    public ExchangeResult updateExchangeResult(ExchangeResult exchangeResult);
    public ExchangeResult createExchangeResult(ExchangeResult exchangeResult);

	public void updateStatus(Long id, Integer status);

	public RewardFlow saveRewardFlow(RewardFlow rewardFlow);
    public RewardFlow findRewardFlowById(Long id);
    public RewardFlow updateRewardFlow(RewardFlow rewardFlow);

	public void doUpdateHoldReward(HoldReward holdReward);

	public List<HoldReward> findHoldReward(String custId, String rewardType, String activityType, boolean isLock);

	/**
	 * 金豆兑换列表查询
	 * @return
	 */
	public List<ExchangeBeanResultVo> findExchangeBeanList(PageVo pageVo);
    public int updateBatchResult(List<Long> exchangeResultIds);

	// ========================================================Yuan=============================================//

	/**
	 * 红包兑现现金列表查询
	 * @param pageVo
	 * @return
	 */
	public List<ExchangeResultVo> findRedPacketExchangeBy(PageVo pageVo);

	/**
	 * 导出成功
	 * @param exchangeResultVos
	 */
	public int exportSuccessfully(List<ExchangeResultVo> exchangeResultVos);


	// ========================================================Yuan=============================================//

}
