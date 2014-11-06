package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.CodeConst;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductParameter;
import com.sunlights.core.vo.ProductVo;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;

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

  public Result findChartBy() {
    ProductParameter productParameter = null;
    Http.RequestBody body = request().body();
    if (body.asJson() != null) {
      productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
    }

    if (body.asFormUrlEncoded() != null) {
      productParameter = productParameterForm.bindFromRequest().get();
    }

    ChartVo chartVo = null;
    if ("1".equals(productParameter.getChartType())) {
      chartVo = productService.findMillionOfProfitsByDays(productParameter.getPrdCode(), productParameter.getInterval());
    }
    if ("2".equals(productParameter.getChartType())) {
      chartVo = productService.findOneWeekProfitsByDays(productParameter.getPrdCode(), productParameter.getInterval());
    }
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

  public Result findProductsByType() {
    PageVo pageVo = new PageVo();
    ProductParameter productParameter = null;
    Http.RequestBody body = request().body();
    if (body.asJson() != null) {
      productParameter = Json.fromJson(body.asJson(), ProductParameter.class);
    }

    if (body.asFormUrlEncoded() != null) {
      productParameter = productParameterForm.bindFromRequest().get();
    }
    System.out.println("[productParameter]" + Json.toJson(productParameter));
    if (productParameter != null) {
      pageVo.setIndex(productParameter.getIndex());
      pageVo.setPageSize(productParameter.getPageSize());
      if (CodeConst.PRODUCT_FUND.equals(productParameter.getCategory())) {
        List<FundVo> fundVos = productService.findFunds(pageVo);
        pageVo.getList().addAll(fundVos);
      }
      if (CodeConst.PRODUCT_RECOMMEND.equals(productParameter.getCategory())) {
        List<ProductVo> productVos = productService.findProductRecommends(pageVo);
        pageVo.getList().addAll(productVos);
      }
      messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
    } else {
      messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_TYPE_EMPTY));
    }


    return ok(messageUtil.toJson());
  }
}
