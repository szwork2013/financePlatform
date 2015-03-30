package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.DepositInterestVo;

import java.util.List;

/**
 * Created by Yuan on 2014/12/16.
 */
public interface DepositInterestService {

	public List<DepositInterestVo> findDepositInterests(PageVo pageVo);

	public void create(DepositInterestVo depositInterestVo);

	public void update(DepositInterestVo depositInterestVo);

	public void delete(DepositInterestVo depositInterestVo);

}
