package web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.QRcodeVo;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.QRcodeService;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: QRcodeController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class QRcodeController extends Controller{

    private QRcodeService service = new QRcodeService();

    /**
     * 获得byte流图片
     * @return
     */
    public Result getQRcodeToByte() {
        String params = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            params = body.asJson().get("params").asText();
        }
        Logger.debug("getQRcodeToByte params：" + Json.toJson(params));

        QRcodeVo qRcodeVo = service.getQRcodeVo(params);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), qRcodeVo);
        return ok(MessageUtil.getInstance().toJson());
    }

}
