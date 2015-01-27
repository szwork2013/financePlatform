/*
 * Project Name:thirdPartyService
 * File Name:JsapiTicket
 * Package Name:weixin
 * Date:2015/1/20 10:19
 * Copyright (c) 2015, YiYue Company All Rights Reserved.
*/
package weixin;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import util.JsonUtil;

import java.io.IOException;
import java.util.Map;

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

    /**
     * 获取token
     *
     * @return
     * @throws java.io.IOException
     */
    public String getAccessToken() throws IOException {
        return getValueByHttpClient(getAccessTokenUrl(), "access_token");
    }

    /**
     * 获取临时船票
     *
     * @param accessToken
     * @return
     * @throws java.io.IOException
     */
    public String getJsapiTicket(String accessToken)throws IOException{
        //获得HttpResponse实例
        return getValueByHttpClient(getTicketUrl(accessToken), "ticket");
    }

    private StringBuffer getAccessTokenUrl() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(ACCESS_TOKEN_URL);
        strBuffer.append("?grant_type=client_credential");
        strBuffer.append("&appid=wx0994aa8f0061604e");
        strBuffer.append("&secret=db2ef3d1e92cea287aba4bfebfcc833e");
        return strBuffer;
    }

    private StringBuffer getTicketUrl(String accessToken) {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(JSAPI_TICKET_URL);
        strBuffer.append("?access_token=");
        strBuffer.append(accessToken);
        strBuffer.append("&type=jsapi");
        return strBuffer;
    }

    private String getValueByHttpClient(StringBuffer strBuffer, String keyName) throws IOException {
        //获得HttpResponse实例
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(strBuffer.toString());
        HttpResponse response = client.execute(request);  //每次在这里有异常 IOEXCEPTION
        //判断是否请求成功
        if (response.getStatusLine().getStatusCode() == 200) {
            //获得返回结果
            String result = EntityUtils.toString(response.getEntity());
            //解析json数据
            Map map = JsonUtil.jsonToMap(result);
            Object accessToken = map.get(keyName);
            if(accessToken !=null){
                return (String) accessToken;
            }
        }
        return "";
    }
}