package com.sunlights.op.service.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dto.BaseXlsDto;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import models.ExchangeResult;
import models.ExchangeScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public interface ExchangeResultService {

	public void updateStatus(ExchangeResult exchangeResult);

	public void genRewardFlow(ExchangeResult exchangeResult, ExchangeScene exchangeScene, Long rewardAmt);

	public ExchangeResult findExchangeResultById(Long id);


    //----兑换

	/**
	 * 金豆兑换列表查询
	 * @return
	 */
	public List<ExchangeBeanResultVo> findExchangeBeanList(PageVo pageVo);

    /**
     * 等待兑换  批量更新为  兑换中
     * @param exchangeResultIds
     */
    public void updateBatchResult(List<Long> exchangeResultIds);

    /**
     * 线下兑换完成结果处理
     * @param dtoList
     */
    public void updateExchangeBeanResult(List<BaseXlsDto> dtoList);

	// ========================================================Yuan=============================================//

	/**
	 * 红包兑换列表查询
	 * @return
	 */
	public List<ExchangeResultVo> findRedPacketExchangeBy(PageVo pageVo);

	/**
	 * 导出成功后更新状态为兑换中
	 * @param exchangeResultVos
	 * @return
	 */
	public int exportSuccessfully(List<ExchangeResultVo> exchangeResultVos);

	/**
	 * 比较是否兑换成功
	 * @param baseXlsDtos
	 * @return 返回兑换成功的记录
	 */
	public int checkExchangeResults(List<BaseXlsDto> baseXlsDtos);

	// ========================================================Yuan=============================================//

}
