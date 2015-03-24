package com.sunlights.core.web;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.FundCategory;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductParameter;
import com.sunlights.core.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.cache.Cache;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: WebProductService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class ProductController extends Controller {
    private Form<ProductParameter> productParameterForm = Form.form(ProductParameter.class);
    private MessageUtil messageUtil = MessageUtil.getInstance();

    private ProductService productService = new ProductServiceImpl();

    public Result findProductsBy() {
        PageVo pageVo = new PageVo();
        ProductParameter productParameter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
        } else if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }

        if (productParameter != null) {
            pageVo.setIndex(productParameter.getIndex());
            pageVo.setPageSize(productParameter.getPageSize());
            pageVo.put("EQS_category", productParameter.getCategory());
            pageVo.put("EQS_productType", productParameter.getType());
            List<FundVo> funds = findProductListFromCache(pageVo);
            pageVo.setList(funds);
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
            return ok(messageUtil.toJson());
        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        return ok(messageUtil.toJson());
    }

    public Result findProductIndex() {
        ProductVo productVo = null;
        List<ProductVo> productVos = findProductIndexFromCache();
        productVo = productVos.isEmpty() ? null : productVos.get(0);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productVo);
        return ok(messageUtil.toJson());
    }

    public Result findProductsIndex() {
        List<ProductVo> productVos = findProductIndexFromCache();
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productVos);
        return ok(messageUtil.toJson());
    }

    public Result findChartBy() {
        ProductParameter prodPara = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            prodPara = Json.fromJson(body.asJson(), ProductParameter.class);
        } else if (body.asFormUrlEncoded() != null) {
            prodPara = productParameterForm.bindFromRequest().get();
        }
        String chartType = prodPara.getChartType();
        ChartVo chartVo = productService.findProfitHistoryByDays(chartType, prodPara.getPrdCode(), prodPara.getInterval());
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), chartVo);
        return ok(messageUtil.toJson());
    }

    public Result findOneWeekProfits() {
        ProductParameter productParameter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
        } else if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }
        ChartVo oneWeekProfitChart = productService.findOneWeekProfitsByDays(productParameter.getCode(), productParameter.getDays());
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), oneWeekProfitChart);
        return ok(messageUtil.toJson());
    }

    public Result findMillionOfProfits() {
        ProductParameter productParameter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
        } else if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }
        ChartVo oneWeekProfitChart = productService.findMillionOfProfitsByDays(productParameter.getCode(), productParameter.getDays());
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), oneWeekProfitChart);
        return ok(messageUtil.toJson());
    }

    public Result findProductDetail() {
        ProductParameter productParameter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
        } else if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }
        ProductVo productDetail = productService.findProductDetailBy(productParameter.getCode(), productParameter.getType());
        if (productDetail != null) {
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productDetail);
        } else {
            messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.NOT_EXISTED_PRODUCT), productDetail);
        }
        return ok(messageUtil.toJson());
    }


    public Result reloadProduct(){
        Cache.remove(AppConst.CACHE_PRODUCT_MONETARY_LIST);
        Cache.remove(AppConst.CACHE_PRODUCT_STF_LIST);
        Cache.remove(AppConst.CACHE_PRODUCT_INDEX);

        productMonetaryListLoad();
        productStfListLoad();
        productIndexListLoad();

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
        return ok(messageUtil.toJson());
    }


    private List<FundVo> findProductListFromCache(PageVo pageVo){
        String category = (String) pageVo.get("EQS_category");
        FundCategory fundCategory = FundCategory.findFundCategoryBy(category);
        if (fundCategory == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_FUND_CATEGORY_EMPTY));
        }

        List<FundVo> cacheList = Lists.newArrayList();
        if (FundCategory.MONETARY.equals(fundCategory)) {
            cacheList = (List) Cache.get(AppConst.CACHE_PRODUCT_MONETARY_LIST);
            if (cacheList == null || cacheList.isEmpty()) {
                cacheList = productMonetaryListLoad();
            }
        } else if (FundCategory.STF.equals(fundCategory)) {
            cacheList = (List) Cache.get(AppConst.CACHE_PRODUCT_STF_LIST);
            if (cacheList == null || cacheList.isEmpty()) {
                cacheList = productStfListLoad();
            }
        }

        List<FundVo> returnList = getProductReturnList(pageVo, cacheList);

        return returnList;
    }

    private List<FundVo> getProductReturnList(PageVo pageVo, List<FundVo> cacheList) {
        int index = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        String type = (String)pageVo.get("EQS_productType");

        List<FundVo> returnList = Lists.newArrayList();
        List<FundVo> meetAllList = Lists.newArrayList();
        for (FundVo fundVo : cacheList) {
            if (StringUtils.isNotEmpty(type) && !type.equals(fundVo.getType())) {
                continue;
            }

            meetAllList.add(fundVo);
        }
        if (index >= meetAllList.size()) {
            return returnList;
        }
        if (index + pageSize > meetAllList.size()) {
            returnList = meetAllList.subList(index, meetAllList.size());
        }else{
            returnList = meetAllList.subList(index, index + pageSize);
        }

        pageVo.setCount(meetAllList.size());
        return returnList;
    }

    private List<ProductVo> findProductIndexFromCache(){
        List<ProductVo> list = (List) Cache.get(AppConst.CACHE_PRODUCT_INDEX);

        if (list == null || list.isEmpty()) {//加载
            list = productIndexListLoad();
        }

        return list;
    }


    private List<FundVo> productMonetaryListLoad() {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_category", AppConst.FUND_CATEGORY_MONETARY);
        List<FundVo> monetaryList = productService.findFunds(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_MONETARY_LIST, monetaryList);
        Logger.info(">>货币型产品列表缓存重新加载完成");

        return monetaryList;
    }
    private List<FundVo> productStfListLoad() {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_category", AppConst.FUND_CATEGORY_STF);
        List<FundVo> stfList = productService.findFunds(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_STF_LIST, stfList);
        Logger.info(">>定期理财型产品列表缓存重新加载完成");
        return stfList;
    }
    private List<ProductVo> productIndexListLoad() {
        PageVo pageVo = new PageVo();
        List<ProductVo> list = productService.findProductIndex(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_INDEX, list);
        Logger.info(">>产品首页缓存重新加载完成");
        return list;
    }
}
