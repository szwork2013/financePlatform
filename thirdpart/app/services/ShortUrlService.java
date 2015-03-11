package services;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;


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
        String apipath = "https://api.weibo.com/2/short_url/shorten.json";
        //https://api.weibo.com/2/short_url/shorten.json?source=5786724301&url_long=http://www.baidu.com
        WSRequestHolder requestHolder = WS.url(apipath)
                .setQueryParameter("source", "5786724301")//App Key
                .setQueryParameter("url_long", path);//路径

        F.Promise<String> jsonPromise = requestHolder.get().map(
                new F.Function<WSResponse, String>() {
                    public String apply(WSResponse response) {
                        JsonNode json = response.asJson();
                        Logger.info("return json:" + json.toString());
                        if (null != json && null != json.get("urls")) {
                            String url_sort = json.get("urls").get(0).get("url_short").toString();
                            if (StringUtils.isNotEmpty(url_sort)) {
                                return url_sort;
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                }
        );

        return jsonPromise.get(8000).replace("\"", "");//去掉双引号
    }
}
