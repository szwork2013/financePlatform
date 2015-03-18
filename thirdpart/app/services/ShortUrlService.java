package services;

import com.sunlights.common.utils.ConfigUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import play.Logger;
import play.libs.Json;


/**
 * Created by Administrator on 2014/12/2.
 */
public class ShortUrlService {
    /**
     * 输入长链接获得短链接
     *
     * @param path（长链接）
     * @return
     */
    public static String getShortURL(String path) {
//        https://api.weibo.com/2/short_url/shorten.json?source=5786724301&url_long=http://www.baidu.com
        Logger.info("==============调用短URL接口============");
        HttpClient httpClient = new HttpClient();
        ConfigUtil.setProxy(httpClient);

        String result = null;
        try {
            String url = "https://api.weibo.com/2/short_url/shorten.json";

            PostMethod postMethod = new PostMethod(url);
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

            NameValuePair[] data = {
                    new NameValuePair("source", "5786724301"),
                    new NameValuePair("url_long", path)};
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);
            Logger.info("调用短URL接口返回statusCode：" + statusCode);

            result = postMethod.getResponseBodyAsString();
            if (statusCode == HttpStatus.SC_OK) {
                Logger.info("调用短URL接口结果为：" + result);
            } else {
                Logger.info("调用短URL接口失败：" + result);
            }
            postMethod.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.info(e.getMessage());
        }

        String url_sort = Json.parse(result).get("urls").get(0).get("url_short").toString();

        return url_sort;
    }
}
