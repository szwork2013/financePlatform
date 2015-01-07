package com.sunlights.customer.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.service.BankService;
import com.sunlights.customer.service.impl.BankCardServiceImpl;
import com.sunlights.customer.service.impl.BankServiceImpl;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.BankCardFormVo;
import com.sunlights.customer.vo.BankCardVo;
import com.sunlights.customer.vo.BankVo;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class BankController extends Controller {

    private Form<PageVo> pagerForm = Form.form(PageVo.class);
    private Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);
    private Form<BankCardVo> bankCardVoForm = Form.form(BankCardVo.class);

    private BankService bankService = new BankServiceImpl();
    private BankCardService bankCardService = new BankCardServiceImpl();
    private CustomerService customerService = new CustomerService();

    public Result createBankCard() {
        Logger.debug(">>params createBankCard :" + Json.toJson(form().bindFromRequest().data()));
        BankCardVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardVoForm.bindFromRequest().get();
        }
        
        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
        bankCardService.createBankCard(customerSession.getCustomerId(), bankCardVo);
        Logger.debug(">>createBankCard return ：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result saveAllBankCard(){
        Map<String,String> params = Form.form().bindFromRequest().data();
        Logger.debug(">>params saveAllBankCard ：" + Json.toJson(params));
        List<BankCardFormVo> list = Lists.newArrayList();
        
        String cards = params.get("cards");
        JsonNode json = Json.parse(cards);
        if (json.isArray()) {
            for (JsonNode jsonNode : json) {
                BankCardFormVo bankCardFormVo = Json.fromJson(jsonNode, BankCardFormVo.class);
                list.add(bankCardFormVo);
            }
        }

        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
        String customerId = customerSession.getCustomerId();

        bankCardService.saveAllBankCard(customerId, list);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        Logger.debug(">>saveAllBankCard return：" + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }







    public Result deleteBankCards() {
        BankCardVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardVoForm.bindFromRequest().get();
        }

        Map<String, String> params = Form.form().bindFromRequest().data();
        Logger.debug("=====deleteBankCards params=====" + Json.toJson(params));
        Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));

        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
        bankCardService.deleteBankCard(Long.valueOf(bankCardVo.getId()));
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.BANK_CARD_DELETE_SUCCESS));
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
        Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        customerService.validateCustomerSession(request(), session(), response());
//        boolean validated = bankService.validateBankCard(token, bankCardVo);
//        if (validated) {
            MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), true);
//        } else {
//            MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.BANK_CARD_CERTIFY_FAIL), false);
//        }
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBankCards() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            pageVo = pagerForm.bindFromRequest().get();
        }
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        customerService.validateCustomerSession(request(), session(), response());
        bankCardService.findBankCardsByToken(token, pageVo);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBankByBankCardNo() {
        BankCardFormVo bankCardVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            bankCardVo = Json.fromJson(body.asJson(), BankCardFormVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            bankCardVo = bankCardForm.bindFromRequest().get();
        }
        Logger.info("[bankCardVo]" + Json.toJson(bankCardVo));
//        BankVo bankVo = bankService.findBankByBankCardNo(bankCardVo.getBankCardNo());
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), null);
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result findBanks() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        if (body.asFormUrlEncoded() != null) {
            pageVo = pagerForm.bindFromRequest().get();
        }
        Logger.info("[pager]" + Json.toJson(pageVo));
        List<BankVo> bankVos = bankService.findBanksBy(pageVo);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), bankVos);
        return ok(MessageUtil.getInstance().toJson());
    }
}
