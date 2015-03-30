package com.sunlights.op.web;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.BankInfoService;
import com.sunlights.op.service.impl.BankInfoServiceImpl;
import com.sunlights.op.vo.BankInfoVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;
import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
@Transactional
public class BankInfoController extends Controller {
    private BankInfoService bankService=new BankInfoServiceImpl();

    /**
     * 含分页的查询银行信息
     * @return
     */
    public Result findBanks() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<BankInfoVo> banklistVos = bankService.findBanks(pageVo);
        pageVo.setList(banklistVos);
        return ok(Json.toJson(pageVo));

//        List<BankInfoVo> banksList = bankService.findBanks(id, title, null);
//        System.out.print("banklist size------>"+banksList.size());
//        return ok(Json.toJson(banksList));
    }

    /**
     * 更新操作
     * @return
     */
    public Result updateBank() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            BankInfoVo bankinfoVos = Json.fromJson(body.asJson(), BankInfoVo.class);
            bankinfoVos.setUpdateTime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            bankService.update(bankinfoVos);
            return ok("更新成功");
        }
        return ok("更新失败");
    }

    public Result createBank(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            BankInfoVo bankinfoVos = Json.fromJson(body.asJson(), BankInfoVo.class);
            bankinfoVos.setUpdateTime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            bankinfoVos.setCreatedTime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            bankService.save(bankinfoVos);
            return ok("创建成功");
        }
        return ok("创建失败");
    }


}
