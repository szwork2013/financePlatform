package com.sunlights.customer.web;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.MsgCenterService;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.impl.MsgCenterServiceImpl;
import com.sunlights.customer.vo.MsgCenterDetailVo;
import com.sunlights.customer.vo.MsgCenterVo;
import models.CustomerMsgReadHistory;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class MsgCenterController extends Controller{
    private Form<PageVo> pageVoForm = form(PageVo.class);
    private MsgCenterService msgCenterService = new MsgCenterServiceImpl();
    private CustomerService customerService = new CustomerService();
    
    public Result findMsgCenterVoList(){
        Logger.info(">>findMsgCenterVoList params：" + Json.toJson(form().bindFromRequest().data()));
        List<MsgCenterVo> list = Lists.newArrayList();
        
        PageVo pageVo = pageVoForm.bindFromRequest().get();
        if (pageVo == null) {
            pageVo = new PageVo();
        }

        String deviceNo = request().getHeader(AppConst.CLIENT_DEVICE);
        Logger.info(">>deviceNo:" + deviceNo);
        pageVo.put("deviceNo", deviceNo);

        CommonUtil.getInstance().validateParams(deviceNo);

        String customerId = null;
        if (request().cookie(AppConst.TOKEN) != null && request().cookie(AppConst.TOKEN).value() != null) {
            CustomerSession customerSession = customerService.getCustomerSession(request().cookie(AppConst.TOKEN).value());
            if (customerSession != null) {
                customerId = customerSession.getCustomerId();
                pageVo.put("customerId", customerId);
                list =  msgCenterService.findMsgCenterVoListWithLogin(pageVo);
            }
        }
        if (customerId == null) {
            list =  msgCenterService.findMsgCenterVoList(pageVo);
        }

        pageVo.setList(list);
        pageVo.getFilter().clear();

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), pageVo);

        Logger.info(">>findMsgCenterVoList return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }
    
    public Result findMsgCenterDetail(){
        Logger.info(">>findMsgCenterDetail params：" + Json.toJson(form().bindFromRequest().data()));

        String customerId = null;
        if (request().cookie(AppConst.TOKEN) != null && request().cookie(AppConst.TOKEN).value() != null) {
            CustomerSession customerSession = customerService.getCustomerSession(request().cookie(AppConst.TOKEN).value());
            if (customerSession != null) {
                customerId = customerSession.getCustomerId();
            }
        }
        String deviceNo = request().getHeader(AppConst.CLIENT_DEVICE);
        Logger.info(">>deviceNo:" + deviceNo);

        Map<String,String> params = form().bindFromRequest().data();
        String msgIdStr = params.get("msgId");
        String sendType = params.get("sendType");
        CommonUtil.getInstance().validateParams(msgIdStr, sendType, deviceNo);

        Long msgId = Long.valueOf(msgIdStr);

        MsgCenterDetailVo msgCenterDetailVo = msgCenterService.findMsgCenterDetail(msgId, sendType);

        CustomerMsgReadHistory customerMsgReadHistory = new CustomerMsgReadHistory();
        customerMsgReadHistory.setCustomerId(customerId);
        customerMsgReadHistory.setPushTxnId(msgId);
        customerMsgReadHistory.setSendType(sendType);
        customerMsgReadHistory.setDeviceNo(deviceNo);
        msgCenterService.saveMsgReadHistory(customerMsgReadHistory);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), msgCenterDetailVo);

        Logger.info(">>findMsgCenterDetail return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result countUnReadNum(){
        Logger.info(">>countUnReadNum params：" + Json.toJson(form().bindFromRequest().data()));

        String customerId = null;
        if (request().cookie(AppConst.TOKEN) != null && request().cookie(AppConst.TOKEN).value() != null) {
            CustomerSession customerSession = customerService.getCustomerSession(request().cookie(AppConst.TOKEN).value());
            if (customerSession != null) {
                customerId = customerSession.getCustomerId();
            }
        }
        String deviceNo = request().getHeader(AppConst.CLIENT_DEVICE);
        Logger.info(">>deviceNo:" + deviceNo);

        CommonUtil.getInstance().validateParams(deviceNo);

        int unReadNum = msgCenterService.countUnReadNum(customerId, deviceNo);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), unReadNum);

        Logger.info(">>countUnReadNum return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result enablePush(){
        Logger.info(">>enablePush params：" + Json.toJson(form().bindFromRequest().data()));

        String registrationId = request().getHeader(AppConst.HEADER_REGISTRATION_ID);
        CommonUtil.getInstance().validateParams(registrationId);

        Logger.info(">>registrationId :" + registrationId);

        msgCenterService.enablePush(registrationId);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.ENABLE_PUSH_SUCCESS));

        Logger.info(">>enablePush return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result disablePush(){
        Logger.info(">>disablePush params：" + Json.toJson(form().bindFromRequest().data()));

        String registrationId = request().getHeader(AppConst.HEADER_REGISTRATION_ID);
        CommonUtil.getInstance().validateParams(registrationId);

        Logger.info(">>registrationId :" + registrationId);

        msgCenterService.disablePush(registrationId);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.DISABLE_PUSH_SUCCESS));

        Logger.info(">>disablePush return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }
    
}
