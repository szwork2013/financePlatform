package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.ReferrerDao;
import com.sunlights.op.dal.impl.ReferrerDaoImpl;
import com.sunlights.op.service.ReferrerService;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import com.sunlights.op.vo.statistics.ReferrerVo;

import java.util.List;

/**
 * Created by Yuan on 2015/1/13.
 */
public class ReferrerServiceImpl implements ReferrerService {

	private ReferrerDao referrerDao = new ReferrerDaoImpl();

	@Override
	public List<ReferrerVo> findReferrers(PageVo pageVo) {
		return referrerDao.findReferrers(pageVo);
	}

	@Override
	public List<ReferrerDetailVo> findReferrerDetails(PageVo pageVo) {
		return referrerDao.findReferrerDetails(pageVo);
	}

}
