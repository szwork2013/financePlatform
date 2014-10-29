package com.sunlights.core.controller;

import com.sunlights.common.page.Pager;
import com.sunlights.common.util.IAppConst;
import com.sunlights.common.util.MessageUtil;
import com.sunlights.core.dal.BankRepository;
import com.sunlights.core.models.Bank;
import com.sunlights.core.vo.BankCardFormVo;
import com.sunlights.core.vo.BankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2014/10/28.
 */
@Named
@Singleton
public class BankController extends Controller {

    private final BankRepository bankRepository;

    private Form<Pager> pagerForm = Form.form(Pager.class);
    private Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);

    @Inject
    public BankController(final BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Result findAllBanks(){
        Iterable<Bank> banks = bankRepository.findAll();
        return ok(Json.toJson(banks));
    }

    public Result test(){
        return ok("hello");
    }

}
