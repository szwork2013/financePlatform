package com.sunlights.common.utils;

import play.Play;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * Created by Administrator on 2014/12/2.
 */
public class ShortURLUtil {
    /**
     * 输入长链接获得短链接
     * @param path（长链接）
     * @return
     */
    public static String getShortURL(String path) {

        String pushUrl = Play.application().configuration().getString("shorturl");
        WSRequestHolder wsRequestHolder = WS.url(pushUrl);
        ObjectNode jsonNode = Json.newObject();
        jsonNode.put("path", path);
        F.Promise<String> jsonPromise = wsRequestHolder.post(jsonNode).map(new F.Function<WSResponse, String>() {
            @Override
            public String apply(WSResponse wsResponse) throws Throwable {
                JsonNode json = wsResponse.asJson();
                return json.toString();
            }
        });

        return jsonPromise.get(10000).replace("\"", "");//去掉双引号
    }

}
