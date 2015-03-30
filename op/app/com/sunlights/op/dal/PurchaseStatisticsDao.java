package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.PurchaseStatisticsVo;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public interface PurchaseStatisticsDao {
	public List<PurchaseStatisticsVo> findFirstPurchaseVos(PageVo pageVo);
	public List<PurchaseStatisticsVo> findUnPurchaseVos(PageVo pageVo);
}
