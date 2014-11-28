package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductParameter;
import models.FundNavHistory;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.test.FakeRequest;
import web.TestUtil;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

@Transactional
public class ProductControllerTest extends BaseTest {
    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<ProductParameter> parameterForm = Form.form(ProductParameter.class);

    @Test
    public void testFindChartBy() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                final ProductParameter parameter = new ProductParameter();
                parameter.setChartType("1");
                parameter.setDays(7);
                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        EntityBaseDao entityBaseDao = new EntityBaseDao();
                        List<FundNavHistory> fundHistories = entityBaseDao.findAll(FundNavHistory.class, "createTime", false);
                        if (!fundHistories.isEmpty()) {
                            parameter.setPrdCode(fundHistories.get(0).getFundcode());
                        }
                    }
                });

                FakeRequest chartRequest = fakeRequest(POST, "/core/product/chart");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);
                FakeRequest formRequest = chartRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formRequest);
                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains("0000");

            }

        });
    }




    @Test
    public void testProductIndex() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                ProductParameter parameter = new ProductParameter();

                // Products Request
                FakeRequest productIndexRequest = fakeRequest(POST, "/core/product/index");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formProductsRequest = productIndexRequest.withHeader(CONTENT_TYPE, TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formProductsRequest);

                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains("0000");

            }

        });
    }

    @Test
    public void testFindProductsAndDetail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                ProductParameter parameter = new ProductParameter();
                parameter.setIndex(0);
                parameter.setPageSize(10);
                parameter.setType(DictConst.FP_PRODUCT_TYPE_1);
                parameter.setCategory(7);

                // Products Request
                FakeRequest fundsRequest = fakeRequest(POST, "/core/products");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formProductsRequest = fundsRequest.withHeader(CONTENT_TYPE, TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formProductsRequest);

                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains(MsgCode.OPERATE_SUCCESS.getCode());

                MessageVo message = toMessageVo(result);
                Object value = message.getValue();
                if (value != null) {
                    PageVo pageVo = Json.fromJson(Json.toJson(value), PageVo.class);
                    if (!pageVo.getList().isEmpty()) {
                        FundVo fundVo = Json.fromJson(Json.toJson(pageVo.getList().get(0)), FundVo.class);
                        // product detail
                        FakeRequest productDetailRequest = fakeRequest(POST, "/core/product/detail");
                        parameter.setType(fundVo.getType());
                        parameter.setCode(fundVo.getCode());
                        paramMap = parameterForm.bind(Json.toJson(parameter)).data();

                        FakeRequest formProductDetailRequest = productDetailRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                        result = route(formProductDetailRequest);

                        Logger.info("result is " + contentAsString(result));
                        assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
                    }
                }

            }

        });
    }
}