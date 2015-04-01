package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.PurchaseStatisticsDao;
import com.sunlights.op.dal.impl.PurchaseStatisticsDaoImpl;
import com.sunlights.op.service.PurchaseStatisticsService;
import com.sunlights.op.vo.PurchaseStatisticsVo;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class PurchaseStatisticsServiceImpl implements PurchaseStatisticsService {
	private PurchaseStatisticsDao purchaseStatisticsDao = new PurchaseStatisticsDaoImpl();

	@Override
	public List<PurchaseStatisticsVo> findFirstPurchaseVos(PageVo pageVo) {
		return purchaseStatisticsDao.findFirstPurchaseVos(pageVo);
	}

	@Override
	public List<PurchaseStatisticsVo> findUnPurchaseVos (PageVo pageVo) {
		return purchaseStatisticsDao.findUnPurchaseVos(pageVo);
	}
}
