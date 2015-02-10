package web;


import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.ShortUrlService;

import java.util.Map;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: ShortUrlController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class ShortUrlController extends Controller {
    

    public Result getShortURL(){
        String path = null;
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            path = body.asJson().get("path").asText();
        }else{
            Map<String, String> params = Form.form().bindFromRequest().data();
            path = params.get("path");
        }
        Logger.debug("getShortURL params：" + path);
        
        String shortUrl = ShortUrlService.getShortURL(path);

        Logger.info("getShortURL return：" + shortUrl);

        return ok(Json.toJson(shortUrl));
    }

    public Result toAppStore(){
        return redirect("https://itunes.apple.com/cn/app/jin-dou-jia-li-cai/id948242790");
    }
}
