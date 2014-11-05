package com.sunlights.account.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.service.impl.CapitalServiceImpl;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * 我的资产的后台服务
 * 提供RESTful接口
 * 
 * @author tangweiqun	2014/10/22
 *
 */
@Transactional
public class CapitalController extends Controller{
	private Form<CapitalFormVo> capitalFormVoForm = Form.form(CapitalFormVo.class);
	
	private CapitalService capitalService = new CapitalServiceImpl();
	
	/**
	 * 获取客户的总资产信息
	 * @return
	 */
    @Transactional
	public Result getTotalCapitalInfo() {
		Form<String> form = Form.form(String.class).bindFromRequest();
		String mobile = form.data().get("mobile");
		System.out.println("mobile = " + mobile);
		
		Logger.info("mobile = === " + mobile);
		TotalCapitalInfo totalCapitalInfo = capitalService.getTotalCapital(mobile, false);
		MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), totalCapitalInfo);
		return ok(MessageUtil.getInstance().toJson());
	}
	
	/**
	 * 获取客户所有产品对应的资产信息
	 * @return
	 */
	public Result getAllCapital4Prd() {
		Form<String> form = Form.form(String.class).bindFromRequest();
		String mobile = form.data().get("mobile");
		System.out.println("mobile = " + mobile);
        CapitalFormVo capitalFormVo = capitalFormVoForm.bindFromRequest().get();
        PageVo pageVo = new PageVo();
        pageVo.setIndex(capitalFormVo.getIndex());
        pageVo.setPageSize(capitalFormVo.getPageSize());

		List<Capital4Product> capital4Products = capitalService.getAllCapital4Product(mobile, pageVo);
        pageVo.setList(capital4Products);

    MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), pageVo);
    return ok(MessageUtil.getInstance().toJson());
}

    /**
     * 获取我的资产所有信息
     * @return
     */
	public Result getMyCapital() {
		Form<String> form = Form.form(String.class).bindFromRequest();
		String mobile = form.data().get("mobile");
		System.out.println("mobile = " + mobile);
		
		TotalCapitalInfo totalCapitalInfo = capitalService.getTotalCapital(mobile, true);

        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        JsonNode json = MessageUtil.getInstance().msgToJson(message, totalCapitalInfo);
		return ok(json);

	}

    /**
     * 累计收益查询
     * @return
     */
    public Result findTotalProfitList(){
        Form<CapitalFormVo> form = Form.form(CapitalFormVo.class).bindFromRequest();
        CapitalFormVo capitalFormVo = form.get();

        List<HoldCapitalVo> list = capitalService.findTotalProfitList(capitalFormVo);

        PageVo pageVo = new PageVo();
        pageVo.setIndex(capitalFormVo.getIndex());
        pageVo.setCount(list.size());
        pageVo.setPageSize(capitalFormVo.getPageSize());
        pageVo.setList(list);

        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        JsonNode json = MessageUtil.getInstance().msgToJson(message, pageVo);
        return ok(json);
    }

}
