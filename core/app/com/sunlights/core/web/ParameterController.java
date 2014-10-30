package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterService;
import com.sunlights.common.Parameter;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

/**
 * <p>Project: fsp</p>
 * <p>Title: ParameterService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

@org.springframework.stereotype.Controller
@Transactional
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    private static final Message operateSuccessMsg = new Message(MsgCode.OPERATE_SUCCESS);
    /**
     * 添加系统参数
     * @return
     */
    public Result addParameter(){
        Map<String, String> params = Form.form().bindFromRequest().data();
        Parameter parameter = parameterService.addParameter(params.get("name"), params.get("value"), params.get("description"));
        return Controller.ok(MessageUtil.getInstance().msgToJson(operateSuccessMsg));
    }

    /**
     * 加载
     * @return
     */
    public Result loadAllParameter(){
        parameterService.loadAllParameter();
        return Controller.ok(MessageUtil.getInstance().msgToJson(operateSuccessMsg));
    }

    public Result clearAll(){
        parameterService.clearAll();
        return Controller.ok(MessageUtil.getInstance().msgToJson(operateSuccessMsg));
    }


    /**
     * 查询
     * @param name
     * @return
     */
    public Result getParameterByName(String name){
        String value = parameterService.getParameterByName(name);
        return Controller.ok(MessageUtil.getInstance().msgToJson(operateSuccessMsg, value));
    }
}
