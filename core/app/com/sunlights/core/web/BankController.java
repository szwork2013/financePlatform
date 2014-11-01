package com.sunlights.core.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.BankCardService;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankCardFormVo;
import com.sunlights.core.vo.BankVo;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */

public class BankController extends Controller{

    private Form<PageVo> pagerForm = Form.form(PageVo.class);
    private Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);


    private BankService bankService;

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
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), true);
        } else {
            MessageUtil.getInstance().setMessage(new Message(MsgCode.BANK_CARD_CERTIFY_FAIL), true);
        }
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBankCards() {
        PageVo pager = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pager = Json.fromJson(body.asJson(), PageVo.class);
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
        MessageUtil.getInstance().setMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), bankVo);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBanks() {
        PageVo pager = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pager = Json.fromJson(body.asJson(), PageVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            pager = pagerForm.bindFromRequest().get();
        }
        play.Logger.info("[pager]" + Json.toJson(pager));
        List<BankVo> bankVos = bankService.findBanksBy(pager);
        MessageUtil.getInstance().setMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), bankVos);
        return ok(MessageUtil.getInstance().toJson());
    }
}
