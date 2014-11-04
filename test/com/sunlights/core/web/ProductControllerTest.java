package com.sunlights.core.web;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.CodeConst;
import com.sunlights.core.vo.AgreementVo;
import com.sunlights.core.vo.ProductParameter;
import models.FundHistory;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.test.FakeRequest;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.*;
import static play.test.Helpers.contentAsString;

@Transactional
public class ProductControllerTest {
    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<ProductParameter> parameterForm = Form.form(ProductParameter.class);

    @Test
    public void testFindChartBy() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                final ProductParameter parameter = new ProductParameter();
                parameter.setChartType("1");

                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        EntityBaseDao entityBaseDao = new EntityBaseDao();
                        List<FundHistory> fundHistories = entityBaseDao.findAll(FundHistory.class, "createTime", false);
                        if (!fundHistories.isEmpty()) {
                            parameter.setPrdCode(fundHistories.get(0).getFundCode());
                        }
                    }
                });

                FakeRequest banksRequest = fakeRequest(POST, "/core/product/chart");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);
                FakeRequest formRequest = banksRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formRequest);
                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains("0000");

            }

        });
    }


    @Test
    public void testFindProductDetail() throws Exception {

    }

    @Test
    public void testFindProductsByType() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                ProductParameter parameter = new ProductParameter();

                parameter.setType(CodeConst.PRODUCT_FUND);
                parameter.setIndex(0);
                parameter.setPageSize(10);
                FakeRequest banksRequest = fakeRequest(POST, "/core/products");
                // form request
                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formRequest = banksRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
                play.mvc.Result result = route(formRequest);

                Logger.info("result is " + contentAsString(result));

                assertThat(contentAsString(result)).contains("0000");

            }

        });
    }
}