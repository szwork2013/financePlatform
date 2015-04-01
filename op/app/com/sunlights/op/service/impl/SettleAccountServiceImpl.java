package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.SettleAccountDao;
import com.sunlights.op.dal.impl.SettleAccountDaoImpl;
import com.sunlights.op.service.SettleAccountService;
import com.sunlights.op.vo.SettleAccountVo;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SettleAccountServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SettleAccountServiceImpl implements SettleAccountService {
    private SettleAccountDao settleAccountDao = new SettleAccountDaoImpl();

    @Override
    public List<SettleAccountVo> findSettleAccountVos(PageVo pageVo) {
        return settleAccountDao.findSettleAccountVos(pageVo);
    }
}
