package com.sunlights.op.jobs;

import com.sunlights.common.FundCategory;
import com.sunlights.crawler.GrabException;
import com.sunlights.crawler.facade.ShuMiFundFacade;
import com.sunlights.op.service.ProductManageService;
import com.sunlights.op.service.impl.ProductManageServiceImpl;
import play.Logger;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: FundGrabService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundGrabService {

    public void grabFund() {
        ProductManageService productManageService = new ProductManageServiceImpl();
        ShuMiFundFacade shuMiFundFacade = new ShuMiFundFacade();
        List<String> productCodes = productManageService.findSynchronousProductCodes();
        Logger.info("[productCodes size ] " + productCodes.size());
        try {
            shuMiFundFacade.grabFundByCode(productCodes, FundCategory.MONETARY, FundCategory.STF);
        } catch (GrabException e) {
            Logger.error("Grab Fund failure", e);
        }
    }

    public void grabFundCode() {
        Logger.info("[begin grab fund Code]" + new Date());
        ShuMiFundFacade shuMiFundFacade = new ShuMiFundFacade();
        try {
            shuMiFundFacade.grabFundCodesByCategories(FundCategory.MONETARY, FundCategory.STF);
        } catch (GrabException e) {
            Logger.error("Grab Fund code failure", e);
        }
        Logger.info("[end grab fund Code]" + new Date());
    }
}
