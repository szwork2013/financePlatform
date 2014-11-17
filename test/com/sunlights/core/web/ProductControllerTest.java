package com.sunlights.core.web;

import com.sunlights.common.DictConst;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.ProductParameter;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.test.FakeRequest;
import web.TestUtil;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

@Transactional
public class ProductControllerTest {
    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<ProductParameter> parameterForm = Form.form(ProductParameter.class);

//    @Test
//    public void testFindChartBy() throws Exception {
//        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
//
//            public void run() {
//                final ProductParameter parameter = new ProductParameter();
//                parameter.setChartType("1");
//
//                JPA.withTransaction(new play.libs.F.Callback0() {
//                    public void invoke() {
//                        EntityBaseDao entityBaseDao = new EntityBaseDao();
//                        List<FundHistory> fundHistories = entityBaseDao.findAll(FundHistory.class, "createTime", false);
//                        if (!fundHistories.isEmpty()) {
//                            parameter.setPrdCode(fundHistories.get(0).getFundCode());
//                        }
//                    }
//                });
//
//                FakeRequest chartRequest = fakeRequest(POST, "/core/product/chart");
//                // form request
//                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
//                Logger.info("[paramMap]" + paramMap);
//                FakeRequest formRequest = chartRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
//                play.mvc.Result result = route(formRequest);
//                Logger.info("result is " + contentAsString(result));
//                assertThat(contentAsString(result)).contains("0000");
//
//            }
//
//        });
//    }
//
//
//    @Test
//    public void testFindProductsByTypeAndDetail() throws Exception {
//        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
//
//            public void run() {
//
//                ProductParameter parameter = new ProductParameter();
//
//                parameter.setCategory(CodeConst.PRODUCT_FUND);
//                parameter.setIndex(0);
//                parameter.setPageSize(10);
//
//                // Products Request
//                FakeRequest productsRequest = fakeRequest(POST, "/core/products");
//                // form request
//                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
//                Logger.info("[paramMap]" + paramMap);
//
//                FakeRequest formProductsRequest = productsRequest.withHeader(CONTENT_TYPE, TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
//                play.mvc.Result result = route(formProductsRequest);
//
//                String contentAsString = contentAsString(result);
//                Logger.info("result is " + contentAsString);
//
//                assertThat(contentAsString).contains("0000");
//
//                // Product Detail Request
//                JsonNode jsonNode = Json.parse(contentAsString);
//                JsonNode value = jsonNode.findValue("value");
//                JsonNode code = value.findValue("code");
//                JsonNode type = value.findValue("type");
//                if (code != null) {
//                    parameter.setCode(code.asText());
//                    parameter.setType(type.asText());
//                    paramMap = parameterForm.bind(Json.toJson(parameter)).data();
//
//                    FakeRequest productDetailRequest = fakeRequest(POST, "/core/product/detail");
//                    FakeRequest formProductDetailRequest = productDetailRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
//                    result = route(formProductDetailRequest);
//                    contentAsString = contentAsString(result);
//                    Logger.info("result is " + contentAsString);
//                    assertThat(contentAsString).contains("0000");
//                }
//
//            }
//
//        });
//    }

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
    public void testFindProducts() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                ProductParameter parameter = new ProductParameter();
                parameter.setIndex(0);
                parameter.setPageSize(10);
                parameter.setType(DictConst.FP_PRODUCT_TYPE_1);
                parameter.setCategory(DictConst.FP_PRODUCT_TYPE_1_1);

                // Products Request
                FakeRequest fundsRequest = fakeRequest(POST, "/core/products");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formProductsRequest = fundsRequest.withHeader(CONTENT_TYPE, TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formProductsRequest);

                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains("0000");

            }

        });
    }
}