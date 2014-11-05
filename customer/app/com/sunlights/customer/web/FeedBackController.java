package com.sunlights.customer.web;


import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.FeedBackService;
import com.sunlights.customer.service.impl.FeedBackServiceImpl;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

import static play.data.Form.form;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FeedBackController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class FeedBackController extends Controller {
    
    private FeedBackService feedBackService = new FeedBackServiceImpl();
    
    public Result saveFeedBack(){
        Map<String, String> params = form().bindFromRequest().data();

        String mobile = params.get("mobile");
        String deviceNo = params.get("deviceNo");
        String content = params.get("feedBack");
        
        feedBackService.saveFeedBack(mobile, deviceNo, content);

        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(MsgCode.OPERATE_SUCCESS));
        
        return ok(json);
    }

}
