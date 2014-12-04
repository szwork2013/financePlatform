package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.ProductParameter;
import com.sunlights.core.vo.ProductVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: AttentionControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class AttentionControllerTest extends BaseTest {

    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<ProductVo> productForm = Form.form(ProductVo.class);
    private static Form<ProductParameter> parameterForm = Form.form(ProductParameter.class);


    @Before
    public void init() {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                login("13811599308", "1");
            }
        });

    }


    @Test
    public void testFindAttentionsAndDelete() {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                PageVo pageVo = new PageVo();
                pageVo.setIndex(0);
                pageVo.setPageSize(10);
                Map<String, String> paramMap = pagerForm.bind(Json.toJson(pageVo)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest attentionsRequest = fakeRequest(POST, "/core/product/attentions");

                FakeRequest formRequest = attentionsRequest.withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                formRequest.withCookies(cookie);
                Result result = route(formRequest);
                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains(MsgCode.OPERATE_SUCCESS.getCode());

                // delete
                MessageVo<LinkedHashMap> vo = toMessageVo(result);
                LinkedHashMap map = vo.getValue();
                try {
                    ConverterUtil.convertMap2Object(map, pageVo);
                    if (!pageVo.getList().isEmpty()) {
                        paramMap = productForm.bind(Json.toJson(pageVo.getList().get(0))).data();
                        FakeRequest cancelAttentionRequest = fakeRequest(POST, "/core/product/attention/cancel");
                        FakeRequest cancelAttentionFormRequest = cancelAttentionRequest.withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                        cancelAttentionFormRequest.withCookies(cookie);
                        result = route(cancelAttentionFormRequest);
                        contentAsString = contentAsString(result);
                        Logger.info("result is " + contentAsString);
                        assertThat(contentAsString).contains(MsgCode.OPERATE_SUCCESS.getCode());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Test
    public void testCreateAttention() {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                ProductVo productVo = new ProductVo();
                productVo.setCode("519505");

                Map<String, String> paramMap = productForm.bind(Json.toJson(productVo)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest attentionsRequest = fakeRequest(POST, "/core/product/attention/create");

                FakeRequest formRequest = attentionsRequest.withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                formRequest.withCookies(cookie);
                Result result = route(formRequest);
                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains(MsgCode.OPERATE_SUCCESS.getCode());

            }

        });
    }

    @Test
    public void testCreateAttentions() {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {
                ProductParameter parameter = new ProductParameter();
                List<String> codes = new ArrayList<String>();
                codes.add("519505");
                codes.add("202301");
                codes.add("161608");
                parameter.setCodes(codes);

                Map<String, String> paramMap = parameterForm.bind(Json.toJson(parameter)).data();

                Logger.info("[paramMap]" + paramMap);

                FakeRequest attentionsRequest = fakeRequest(POST, "/core/product/attentions/create");

                FakeRequest formRequest = attentionsRequest.withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                formRequest.withCookies(cookie);
                Result result = route(formRequest);
                String contentAsString = contentAsString(result);
                Logger.info("result is " + contentAsString);

                assertThat(contentAsString).contains(MsgCode.OPERATE_SUCCESS.getCode());

            }

        });
    }
}
