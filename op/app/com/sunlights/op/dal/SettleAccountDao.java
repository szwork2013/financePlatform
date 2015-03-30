package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.SettleAccountVo;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SettleAccountDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface SettleAccountDao {
    public List<SettleAccountVo> findSettleAccountVos(PageVo pageVo);

    public List<SettleAccountVo> findSettleAccountStatisic(PageVo pageVo);
}
