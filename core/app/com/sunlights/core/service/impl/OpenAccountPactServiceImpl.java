package com.sunlights.core.service.impl;

import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.dal.OpenAccountPactDao;
import com.sunlights.core.models.OpenAccountPact;
import com.sunlights.core.vo.AgreementVo;
import com.sunlights.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Service
public class OpenAccountPactServiceImpl implements OpenAccountPactService {
    @Autowired
    private OpenAccountPactDao openAccountPactDao;

    @Override
    public AgreementVo findAgreementVoByAgreementNo(String agreementNo) {
        if (StringUtils.isBlankOrNull(agreementNo)) {
            return null;
        }
        OpenAccountPact openAccountPact = openAccountPactDao.findOpenAccountPactByAgreementNo(agreementNo);
        if (openAccountPact == null) {
            return null;
        }
        return new AgreementVo(openAccountPact);
    }
}
