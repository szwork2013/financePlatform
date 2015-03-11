/*
 * Project Name:thirdPartyService
 * File Name:JsonUtil
 * Package Name:util
 * Date:2015/1/20 11:41
 * Copyright (c) 2015, YiYue Company All Rights Reserved.
*/
package util;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:    JsonUtil
 * Description:  ADD Description.
 * Date:         2015/1/20 11:41
 *
 * @author Xuelong.Gu
 * @version 1.0
 * @since JDK 1.6
 */
public class JsonUtil {
    /**
     * json to map
     *
     * @param jsonStr
     * @return
     */
    public static Map jsonToMap(String jsonStr) {
        try {
            JsonNode jsonNode = Json.parse(jsonStr);
            Map map = Json.fromJson(jsonNode, Map.class);
            return map;
        } catch (Exception e) {
            Logger.error("Json解析失败，错误信息为：" + e.getMessage());
            return new HashMap();
        }
    }

}