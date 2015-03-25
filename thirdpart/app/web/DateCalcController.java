package web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.joda.time.LocalDate;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.DateCalcService;

import java.text.MessageFormat;
import java.util.Map;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: DateCalcController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

@Api(value = "/thirdpart", description = "第三方服务接口")
@Transactional
public class DateCalcController extends Controller{
    private DateCalcService dateCalcService = new DateCalcService();


    @ApiOperation(value = "获取交易日",
            nickname = "datecalc",
            response = MessageVo.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "交易的时间(2015-02-13 14:59:59)", required = true, paramType = "form"),
            @ApiImplicitParam(name = "durationDays", value = "间隔天数(1)", required = true, paramType = "form")
    })
    public Result dateCalc(){
        String startDateStr = null;
        int durationInDays = 0;

        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            startDateStr = body.asJson().get("startDate").asText();
            durationInDays = Integer.valueOf(body.asJson().get("durationDays").asText());
        } else {
            Map<String, String> params = Form.form().bindFromRequest().data();
            startDateStr = params.get("startDate");
            durationInDays = Integer.valueOf(params.get("durationDays"));

            Logger.info(MessageFormat.format(">>params:startDate={0},durationDays={1}", startDateStr, durationInDays));
        }

        LocalDate localDate = dateCalcService.getEndTradeDate(startDateStr, durationInDays);

        String result = localDate.toString("yyyy-MM-dd").replaceAll("\"", "");

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), result);
        Logger.info(">>result:" + MessageUtil.getInstance().toJson());

        return ok(MessageUtil.getInstance().toJson());
    }
}
