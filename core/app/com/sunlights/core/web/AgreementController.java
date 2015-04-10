package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.factory.CoreFactory;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.vo.AgreementVo;
import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * <p>Project: fsp</p>
 * <p>Title: WebAgreementService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class AgreementController extends Controller {
    private static MessageUtil messageUtil = MessageUtil.getInstance();
    private static Form<AgreementVo> agreementVoForm = Form.form(AgreementVo.class);
    private OpenAccountPactService openAccountPactService = CoreFactory.getOpenAccountPactService();

    public Result findAgreementVoByAgreementNo() {
        Http.RequestBody body = request().body();
        AgreementVo agreementVo = new AgreementVo();
        if (body.asFormUrlEncoded() != null) {
            agreementVo = agreementVoForm.bindFromRequest().get();
        }
        if (StringUtils.isEmpty(agreementVo.getCode())) {
            messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_EMPTY_PROTOCOL_NO));
            return ok(messageUtil.toJson());
        }
        AgreementVo av = openAccountPactService.findAgreementVoByAgreementNo(agreementVo.getCode());

        if (av == null) {
            messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_PROTOCOL_NONE));
        } else {
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), av);
        }
        return ok(messageUtil.toJson());
    }
}
