package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import com.sunlights.op.vo.statistics.ReferrerVo;

import java.util.List;

/**
 * Created by Administrator on 2015/1/14.
 */
public interface ReferrerDao {

	/**
	 * Referrer List
	 * @param pageVo
	 * @return
	 * @author Yuan
	 */
	public List<ReferrerVo> findReferrers(PageVo pageVo);

	/**
	 * ReferrerDetail List
	 * @param pageVo
	 * @return
	 * @author Yuan
	 */
	public List<ReferrerDetailVo> findReferrerDetails(PageVo pageVo);
}
