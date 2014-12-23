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
import models.CustomerSession;
import play.data.Form;
import play.db.jpa.Transactional;
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
        List<MsgCenterVo> list = Lists.newArrayList();
        
        PageVo pageVo = pageVoForm.bindFromRequest().get();
        if (pageVo == null) {
            pageVo = new PageVo();
        }
        String customerId = null;
        if (request().cookie(AppConst.TOKEN) != null && request().cookie(AppConst.TOKEN).value() != null) {
            CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
            customerId = customerSession.getCustomerId();
            pageVo.put("customerId", customerId);
            list =  msgCenterService.findMsgCenterVoListWithLogin(pageVo);
        }else{
            list =  msgCenterService.findMsgCenterVoList(pageVo);
        }

        pageVo.setList(list);
        pageVo.getFilter().clear();

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(MessageUtil.getInstance().toJson());
    }
    
    public Result findMsgCenterDetail(){
        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
        String customerId = customerSession.getCustomerId();

        Map<String,String> params = form().bindFromRequest().data();
        String msgIdStr = params.get("msgId");
        String sendType = params.get("sendType");
        CommonUtil.getInstance().validateParams(msgIdStr, sendType);

        Long msgId = Long.valueOf(msgIdStr);

        MsgCenterDetailVo msgCenterDetailVo = msgCenterService.findMsgCenterDetail(msgId, sendType);

        msgCenterService.createMsgReadHistory(customerId, msgId, sendType);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), msgCenterDetailVo);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result countUnReadNum(){
        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
        String customerId = customerSession.getCustomerId();

        int unReadNum = msgCenterService.countUnReadNum(customerId);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), unReadNum);
        return ok(MessageUtil.getInstance().toJson());
    }
    
}
