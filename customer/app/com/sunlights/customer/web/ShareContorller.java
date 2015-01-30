package com.sunlights.customer.web;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.QRcodeByte;
import models.Customer;
import models.CustomerSession;

import org.apache.commons.lang3.StringUtils;

import play.Configuration;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.QRcodeService;
import sun.misc.BASE64Encoder;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.QRcodeVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ShareInfoServiceFactory;
import com.sunlights.customer.service.ShareInfoService;
import com.sunlights.customer.vo.ShareInfoContext;
import com.sunlights.customer.vo.ShareInfoVo;
import com.sunlights.customer.vo.ShareVo;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/3.
 */
@Transactional
public class ShareContorller extends ActivityBaseController {


    private QRcodeService qRcodeService = new QRcodeService();
    private Form<ShareVo> shareParameterForm = Form.form(ShareVo.class);

    /**
     * 获得byte流图片
     *
     * @return
     */
    public Result getQRcodeToByte() {
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();//获得客户id
        if (StringUtils.isEmpty(custNo)) {
            return notFound("用户登录已经超时,请重新登录");
        }
        ShareVo shareVo = getShareVo();
        String type = shareVo.getType();
        String id = shareVo.getId();
        if (StringUtils.isEmpty(type)) {
            type = ActivityConstant.SHARE_TYPE_INVITER;
        }
        ShareInfoService shareInfoService = ShareInfoServiceFactory.createShareInfoService(type);
        if (shareInfoService == null) {
            Logger.error("不支持的分享类型");
            Message message = new Message(Severity.INFO, MsgCode.NOT_SUPPORT_SHARE_TYPE);
            messageUtil.setMessage(message);
            return ok(messageUtil.toJson());
        }

        ShareInfoContext context = getShareInfoContext(id, custNo, type);
        ShareInfoVo shareInfoVo = shareInfoService.getShareInfoByType(context);
        String shorturl = shareInfoVo.getShortUrl();
        QRcodeVo qRcodeVo = qRcodeService.getQRcodeVo(shorturl);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), qRcodeVo);
        return ok(messageUtil.toJson());

    }

    public Result getQRcodeWithToken() {
        CustomerSession customerSession = getCustomerSession();
      String custNo = customerSession.getCustomerId();//获得客户id
        if (StringUtils.isEmpty(custNo)) {
           messageUtil.setMessage(new Message(MsgCode.LOGIN_TIMEOUT));
 }else{
            Customer customer = customerService.getCustomerByCustomerId(custNo);
            String mobile =customer.getMobile();
            String params = "";
            if (request().body().asJson() != null) {
                params = request().body().asJson().get("params").asText();
            }else{
                Map<String, String> map = Form.form().bindFromRequest().data();
                params = map.get("params");
            }

            Logger.debug("getQRcodeToByte params：" + Json.toJson(params));

            CommonUtil.getInstance().validateParams(params);

            QRcodeByte qrcode = new QRcodeByte();        //将内容存入对象‘

            Logger.info(">> " + params + mobile);
            byte[] pngData = qrcode.generateQRCode(params + mobile);//加入短路径,如："http://t.cn/RzJWtFA"

            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), new BASE64Encoder().encode(pngData));
      }

        return ok(messageUtil.toJson());

    }

    private ShareInfoContext getShareInfoContext(String id, String custNo, String type) {
        ShareInfoContext context = new ShareInfoContext();
        context.setRefId(id);
        context.setCustNo(custNo);
        context.setType(type);
        return context;
    }

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
        if(ActivityConstant.SHARE_TYPE_INVITER.equals(type) && Configuration.root().getBoolean("inviter")) {
            type = ActivityConstant.SHARE_TYPE_ACTIVITY;
            id = ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE;
        }
        ShareInfoService shareInfoService = ShareInfoServiceFactory.createShareInfoService(type);
        if (shareInfoService == null) {
            Logger.error("不支持的分享类型");
            message = new Message(Severity.INFO, MsgCode.NOT_SUPPORT_SHARE_TYPE);
            messageUtil.setMessage(message);
            return ok(messageUtil.toJson());
        }
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
