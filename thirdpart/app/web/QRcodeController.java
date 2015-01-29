package web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.QRcodeVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.QRcodeService;

import java.util.Map;

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

        Http.RequestBody body = request().body();

        String params = null;
        if (body.asJson() != null) {
            params = body.asJson().get("params").asText();
        }

        if(StringUtils.isEmpty(params)) {
            Map<String, String[]> formUrl = body.asFormUrlEncoded();
            if(formUrl != null){
                String[] value = formUrl.get("params");
                if(value!=null && value.length > 0){
                    params = value[0];
                }
            }
        }

        Logger.debug("getQRcodeToByte params：" + Json.toJson(params));

        if(StringUtils.isEmpty(params)){
            MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.CODE_EXIST_ERROR, "params cannot be empty"));
            return ok(MessageUtil.getInstance().toJson());
        }else {
            QRcodeVo qRcodeVo = service.getQRcodeVo(params);
            MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), qRcodeVo.getQrcodeByte());
            return ok(MessageUtil.getInstance().toJson());
        }
    }

}
