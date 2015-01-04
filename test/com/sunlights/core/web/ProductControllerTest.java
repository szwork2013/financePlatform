package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.*;
import org.fest.assertions.Assertions;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.test.FakeRequest;
import web.TestUtil;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

@Transactional
public class ProductControllerTest extends BaseTest {
    private static Form<ProductParameter> parameterForm = Form.form(ProductParameter.class);



    @Test
    public void testFindChartBy() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                final ProductParameter parameter = new ProductParameter();
                parameter.setChartType("2");
                parameter.setInterval(7);
                parameter.setPrdCode("482002");
                FakeRequest chartRequest = fakeRequest(POST, "/core/product/chart");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);
                FakeRequest formRequest = chartRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formRequest);
                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);


                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("CoreProductsChart.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo message = toMessageVo(result);
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message
                ChartVo testChartVo = Json.fromJson(Json.toJson(testMessage.getValue()), ChartVo.class);
                ChartVo ChartVo = Json.fromJson(Json.toJson(message.getValue()), ChartVo.class);
                assertThat(testChartVo).isEqualTo(ChartVo);//此处判断value


                MessageVo<LinkedHashMap> vo = toMessageVo(result);
                LinkedHashMap map = vo.getValue();
                if (map != null) {
                    try {
                        ChartVo chartVo = ConverterUtil.convertMap2Object(map, new ChartVo());
                        Logger.info(chartVo.getPoints().toString());
                        Logger.info("result is :" + chartVo.getPoints().size());
                        Assertions.assertThat(chartVo.getPoints().size()).isEqualTo(7);
                    } catch (ConverterException e) {
                        e.printStackTrace();
                    }
                }
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

                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("Fund.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo message = toMessageVo(result);
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message
                FundVo testfundVo = Json.fromJson(Json.toJson(testMessage.getValue()), FundVo.class);
                FundVo fundVo = Json.fromJson(Json.toJson(message.getValue()), FundVo.class);
                assertThat(testfundVo).isEqualTo(fundVo);//此处判断value


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
                parameter.setCategory("MONETARY");//MONETARY

                // Products Request
                FakeRequest fundsRequest = fakeRequest(POST, "/core/products");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formProductsRequest = fundsRequest.withHeader(CONTENT_TYPE, TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formProductsRequest);

                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);


                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("CoreProducts.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo message = toMessageVo(result);
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message
                PageVo pageVo = Json.fromJson(Json.toJson(message.getValue()), PageVo.class);
                PageVo testPageVo = Json.fromJson(Json.toJson(testMessage.getValue()), PageVo.class);
                assertThat(testPageVo).isEqualTo(pageVo);//此处判断page

                Object value = message.getValue();
                if (value != null) {
                   // PageVo pageVo = Json.fromJson(Json.toJson(value), PageVo.class);
                    if (!pageVo.getList().isEmpty()) {
                        FundVo fundVo = Json.fromJson(Json.toJson(pageVo.getList().get(0)), FundVo.class);
                        FundVo testFundVo = Json.fromJson(Json.toJson(testPageVo.getList().get(0)), FundVo.class);
                        assertThat(fundVo).isEqualTo(testFundVo);//此处判断list
                        // product detail
                        FakeRequest productDetailRequest = fakeRequest(POST, "/core/product/detail");
                        parameter.setType(fundVo.getType());
                        parameter.setCode(fundVo.getCode());
                        paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                        FakeRequest formProductDetailRequest = productDetailRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                        result = route(formProductDetailRequest);
                        Logger.info("result is " + contentAsString(result));

                        /**
                         * 验证message与value
                         */
                        String testString1= null;
                        try {
                            testString1 = getJsonFile("CoreProductsDetail.json");//获得json文件内容
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MessageVo message1 = toMessageVo(result);
                        MessageVo testMessage1 = toMessageVo(testString1);
                        assertThat(testMessage1).isEqualTo(message1);//此处判断message
                        FundDetailVo testfundVo1 = Json.fromJson(Json.toJson(testMessage1.getValue()), FundDetailVo.class);
                        FundDetailVo fundVo1 = Json.fromJson(Json.toJson(message1.getValue()), FundDetailVo.class);
                        assertThat(testfundVo1).isEqualTo(fundVo1);//此处判断value

                    }
                }

            }

        });
    }

    @Test
    public void testFindSTFProducts() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                ProductParameter parameter = new ProductParameter();
                parameter.setIndex(0);
                parameter.setPageSize(10);
                parameter.setType(DictConst.FP_PRODUCT_TYPE_1);
                parameter.setCategory("STF");

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

            }

        });
    }
}