package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.vo.AgreementVo;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.mvc.Http;
import play.mvc.Result;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * <p>Project: fsp</p>
 * <p>Title: WebAgreementService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@org.springframework.stereotype.Controller
public class AgreementController {
    private static MessageUtil messageUtil = MessageUtil.getInstance();
    private static Form<AgreementVo> agreementVoForm = Form.form(AgreementVo.class);

    private OpenAccountPactService openAccountPactService;


    public Result findAgreementVoByAgreementNo() {
        Http.RequestBody body = request().body();
        AgreementVo agreementVo = new AgreementVo();
        if (body.asFormUrlEncoded() != null) {
            agreementVo = agreementVoForm.bindFromRequest().get();
        }
        if (StringUtils.isNotEmpty(agreementVo.getCode())) {
            messageUtil.addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.SEARCH_FAIL_EMPTY_PROTOCOL_NO));
            return ok(messageUtil.toJson());
        }
        AgreementVo av = openAccountPactService.findAgreementVoByAgreementNo(agreementVo.getCode());
        if(av == null) {
            messageUtil.addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.SEARCH_FAIL_PROTOCOL_NONE));
        } else {
            messageUtil.addMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), av);
        }
        return ok(messageUtil.toJson());
    }
}
