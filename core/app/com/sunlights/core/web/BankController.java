package com.sunlights.core.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.core.service.BankCardService;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankVo;
import com.sunlights.core.vo.BankCardFormVo;
import com.sunlights.common.page.Pager;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@org.springframework.stereotype.Controller
public class BankController {

    private Form<Pager> pagerForm = Form.form(Pager.class);
    private Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);

    @Autowired
    private BankService bankService;
    @Autowired
    private BankCardService bankCardService;

    public Result createBankCard() {
        BankCardFormVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardFormVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardForm.bindFromRequest().get();
        }
        play.Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        bankCardService.createBankCard(token, bankCardVo);
        return ok(MessageUtil.getInstance().toJson());
    }


    public Result deleteBankCards() {
        BankCardFormVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardFormVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardForm.bindFromRequest().get();
        }
        play.Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        bankCardService.deleteBankCard(token, bankCardVo);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result validateBankCard() {
        BankCardFormVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardFormVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardForm.bindFromRequest().get();
        }
        play.Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        boolean validated = bankService.validateBankCard(token, bankCardVo);
        if (validated) {
            MessageUtil.getInstance().addMessage(new Message(MsgCode.OPERATE_SUCCESS), true);
        } else {
            MessageUtil.getInstance().addMessage(new Message(MsgCode.BANK_CARD_CERTIFY_FAIL), true);
        }
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBankCards() {
        Pager pager = new Pager();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pager = Json.fromJson(body.asJson(), Pager.class);
        }
        if (body.asFormUrlEncoded() != null) {
            pager = pagerForm.bindFromRequest().get();
        }
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        bankCardService.findBankCardsByToken(token, pager);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBankByBankCardNo() {
        String bankCardNo = null;
        BankVo bankVo = bankService.findBankByBankCardNo(bankCardNo);
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), bankVo);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBanks() {
        Pager pager = new Pager();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pager = Json.fromJson(body.asJson(), Pager.class);
        }
        if (body.asFormUrlEncoded() != null) {
            pager = pagerForm.bindFromRequest().get();
        }
        play.Logger.info("[pager]" + Json.toJson(pager));
        List<BankVo> bankVos = bankService.findBanksBy(pager);
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), bankVos);
        return ok(MessageUtil.getInstance().toJson());
    }
}
