package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.BankVo;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface BankService {

	public List<BankVo> findBanksBy (PageVo pageVo);

	public void save (BankVo bankVo);

	public void delete (BankVo bankVo);
}
