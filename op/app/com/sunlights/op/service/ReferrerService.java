package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import com.sunlights.op.vo.statistics.ReferrerVo;

import java.util.List;

/**
 * Created by Yuan on 2015/1/13.
 */
public interface ReferrerService {

	public List<ReferrerVo> findReferrers(PageVo pageVo);

	public List<ReferrerDetailVo> findReferrerDetails(PageVo pageVo);

}
