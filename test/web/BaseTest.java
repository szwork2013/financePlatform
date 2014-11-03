package web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.vo.MessageVo;
import play.libs.Json;
import play.mvc.Result;

import static play.test.Helpers.contentAsString;

/**
 * Created by Administrator on 2014/11/3.
 */
public class BaseTest {

    protected MessageVo toMessageVo(Result result) {
        String content = contentAsString(result);
        JsonNode jsonNode = Json.parse(content);
        return Json.fromJson(jsonNode, MessageVo.class);
    }
}
