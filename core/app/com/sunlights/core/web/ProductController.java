package com.sunlights.core.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductParameter;
import com.sunlights.core.vo.ProductVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.CustomerSession;
import org.apache.commons.beanutils.ConvertUtils;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        }

        if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }

        if (productParameter != null) {
            pageVo.setIndex(productParameter.getIndex());
            pageVo.setPageSize(productParameter.getPageSize());
            pageVo.put("EQS_category", productParameter.getCategory());
            pageVo.put("EQS_productType", productParameter.getType());
            List<FundVo> funds = productService.findFunds(pageVo);
            pageVo.setList(funds);
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
            return ok(messageUtil.toJson());
        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        return ok(messageUtil.toJson());
    }

    public Result findProductIndex() {
		ProductVo productVo = null;
        List<ProductVo> productVos = productService.findProductIndex(new PageVo());
		productVo = productVos.isEmpty() ? null : productVos.get(0);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productVo);
        return ok(messageUtil.toJson());
    }

    public Result findProductsIndex() {
        List<ProductVo> productVos = productService.findProductIndex(new PageVo());
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productVos);
        return ok(messageUtil.toJson());
    }

    public Result findChartBy() {
        ProductParameter prodPara = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            prodPara = Json.fromJson(body.asJson(), ProductParameter.class);
        }
        if (body.asFormUrlEncoded() != null) {
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
        }

        if (body.asFormUrlEncoded() != null) {
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
        }

        if (body.asFormUrlEncoded() != null) {
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
        }

        if (body.asFormUrlEncoded() != null) {
            productParameter = productParameterForm.bindFromRequest().get();
        }
        ProductVo productDetail = productService.findProductDetailBy(productParameter.getCode(), productParameter.getType());
        if (productDetail != null) {
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), productDetail);
        } else {
            messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_PRODUCT_DETAIL), productDetail);
        }
        return ok(messageUtil.toJson());
    }

}
