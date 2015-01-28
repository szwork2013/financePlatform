/*
 * Project Name:thirdPartyService
 * File Name:JsapiTicket
 * Package Name:weixin
 * Date:2015/1/20 10:19
 * Copyright (c) 2015, YiYue Company All Rights Reserved.
*/
package weixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sunlights.common.utils.ConfigUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import play.Logger;
import util.JsonUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:    JsapiTicket
 * Description:  ADD Description.
 * Date:         2015/1/20 10:19
 *
 * @author Xuelong.Gu
 * @version 1.0
 * @since JDK 1.6
 */
public class JsapiTicket {
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";


    private static final String tokenKey = "weixin_token";
    private static Cache<String, String> cacheFormCallable = null;

    static {
        try {
            cacheFormCallable = createCallableCache();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private static final JsapiTicket instance = new JsapiTicket();

    private JsapiTicket(){}

    public static final JsapiTicket getInstance(){
        return instance;
    }

    /**
     * 创建一个缓存对象
     * @throws Exception
     */
    private static Cache<String, String> createCallableCache() throws Exception {
        Cache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(1)
                .expireAfterWrite(119, TimeUnit.MINUTES)
                .build();
        return cache;
    }


    public String getTicket() {
        try {
            //Callable只有在缓存值不存在时，才会调用
            return cacheFormCallable.get(tokenKey, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String accessToken = getAccessToken();
                    return getValueByHttpClient(getTicketUrl(accessToken), "ticket");
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token,
     *
     * @return
     * @throws java.io.IOException
     */
    private String getAccessToken() throws IOException {
        return getValueByHttpClient(getAccessTokenUrl(), "access_token");
    }

    private String getValueByHttpClient(String tiketUrl, String keyName) throws IOException {
        //获得HttpResponse实例
        HttpClient client = new HttpClient();
        ConfigUtil.setProxy(client);
        GetMethod getMethod = new GetMethod(tiketUrl);

        int statusCode = client.executeMethod(getMethod);
        String accessCode = "";
        Logger.info("调用短信接口返回statusCode：" + statusCode);
        //判断是否请求成功
        if (statusCode == 200) {
            //获得返回结果
            String result = getMethod.getResponseBodyAsString();
            //解析json数据
            Map map = JsonUtil.jsonToMap(result);
            Object value = map.get(keyName);
            if(value !=null){
                accessCode = (String)value;
            }
        }

        return accessCode;
    }

    private String getAccessTokenUrl() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(ACCESS_TOKEN_URL);
        strBuffer.append("?grant_type=client_credential");
        strBuffer.append("&appid=wx0994aa8f0061604e");
        strBuffer.append("&secret=db2ef3d1e92cea287aba4bfebfcc833e");
        return strBuffer.toString();
    }

    private String getTicketUrl(String accessToken) {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(JSAPI_TICKET_URL);
        strBuffer.append("?access_token=");
        strBuffer.append(accessToken);
        strBuffer.append("&type=jsapi");
        return strBuffer.toString();
    }


}