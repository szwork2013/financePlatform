package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.factory.ShareInfoServiceFactory;
import com.sunlights.customer.service.share.ShareInfoContext;
import com.sunlights.customer.service.share.ShareInfoService;
import com.sunlights.customer.vo.CustomerVo;
import com.sunlights.customer.vo.ShareInfoVo;
import com.sunlights.customer.vo.ShareVo;
import com.wordnik.swagger.annotations.*;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.text.MessageFormat;

/**
 * Created by Administrator on 2014/12/3.
 */
@Transactional
public class ShareController extends ActivityBaseController {


    private Form<ShareVo> shareParameterForm = Form.form(ShareVo.class);

    private ShareVo getShareVo() {
        Http.RequestBody body = request().body();
        ShareVo shareVo = null;
        if (body.asJson() != null) {
            shareVo = Json.fromJson(body.asJson(), ShareVo.class);
        } else if (body.asFormUrlEncoded() != null) {
            shareVo = shareParameterForm.bindFromRequest().get();
        }
        return shareVo;
    }

    /**
     * 分享接口
     *
     * @return
     */
    @ApiOperation(value = "查询分享信息接口",
            notes = "MessageVo 的value是ShareVo对象", response = MessageVo.class, nickname = "share", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", required = true, paramType = "form"),
            @ApiImplicitParam(name = "type", paramType = "form")})
    @ApiResponses(value = {@ApiResponse(code = 0223, message = "分享查询成功", response = ShareVo.class),
            @ApiResponse(code = 2229, message = "不支持的分享类型")
    })
    public Result share() {
        String custNo = null;
        Message message = null;
        try {
            CustomerSession customerSession = getCustomerSession();
            custNo = customerSession.getCustomerId();//获得客户id
        } catch (Exception e) {
            Logger.debug("没有登录。。");
        }
        ShareVo shareVo = getShareVo();
        String type = shareVo.getType();
        String id = shareVo.getId();

        Logger.info(MessageFormat.format(">>share params--->type:{0}, id:{1}", type, id));

        ShareInfoService shareInfoService = ShareInfoServiceFactory.createShareInfoService(type);

        message = new Message(Severity.INFO, MsgCode.SHARE_QUERY_SUCC);
        try {
            ShareInfoContext context = new ShareInfoContext();
            context.setRefId(id);
            context.setCustNo(custNo);
            context.setType(type);
            ShareInfoVo shareInfoVo = shareInfoService.getShareInfoByType(context);
            shareVo = new ShareVo();
            shareVo.setId(id);
            shareVo.setType(type);
            shareVo.setShorturl(shareInfoVo.getShortUrl());
            shareVo.setContent(shareInfoVo.getContent());
            shareVo.setImageurl(shareInfoVo.getImageUrl());
            shareVo.setTitle(shareInfoVo.getTitle());
            messageUtil.setMessage(message, shareVo);
            Logger.debug("返回给前端的内容----》:" + messageUtil.toJson());
            return ok(messageUtil.toJson());
        } catch (BusinessRuntimeException be) {
            if (MsgCode.LOGIN_TIMEOUT.getCode().equals(be.getErrorCode())) {
                message = new Message(Severity.INFO, MsgCode.LOGIN_TIMEOUT);
                message.setSummary("您还没有登录");
                Logger.error("您还没有登录", be);
            }
        } catch (Exception e) {
            message = new Message(Severity.INFO, MsgCode.ACTIVITY_SYS_ERROR);
            message.setSummary("系统异常");
            Logger.error("系统异常", e);
        }
        messageUtil.setMessage(message);
        return ok(messageUtil.toJson());
    }
}
