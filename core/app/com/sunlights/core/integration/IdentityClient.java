package com.sunlights.core.integration;

import play.Logger;
import play.libs.Json;
import cn.com.nciic.www.SimpleCheckByJson;
import cn.com.nciic.www.SimpleCheckByJsonResponse;
import cn.com.nciic.www.service.IdentifierServiceStub;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.core.vo.IdentifierVo;

/**
 * <p>Project: fsp</p>
 * <p>Title: IdentityService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */


public class IdentityClient {

    private ParameterService parameterService = new ParameterService();

    public void identity(String idCardNo, String userName){
        ObjectNode CheckRequest = Json.newObject();
        CheckRequest.put("IDNumber", idCardNo);
        CheckRequest.put("Name", userName);
        String req = Json.toJson(CheckRequest).toString();
        Logger.info("================CheckRequest:" + req);

        ObjectNode Credential = Json.newObject();
        Credential.put("UserName", parameterService.getParameterByName(AppConst.CERTIFY_USERNAME));
        Credential.put("Password", parameterService.getParameterByName(AppConst.CERTIFY_PASSWORD));
        String cred = Json.toJson(Credential).toString();
        Logger.info("================Credential:" + cred);

        String url = parameterService.getParameterByName(AppConst.CERTIFY_URL);
        String testMode = parameterService.getParameterByName(AppConst.CERTIFY_TEST);
        String returnStr = null;
        if (AppConst.STATUS_VALID.equals(testMode)) {
            Logger.info("================实名认证==============");
            try {
                SimpleCheckByJson scbj = new SimpleCheckByJson();
                scbj.setCred(cred);
                scbj.setRequest(req);

                IdentifierServiceStub client = new IdentifierServiceStub(url);
                SimpleCheckByJsonResponse scbr = client.simpleCheckByJson(scbj);
                returnStr = scbr.getSimpleCheckByJsonResult();
                Logger.info("==============实名认证返回结果：" + returnStr);
            } catch (Exception e) {
                throw CommonUtil.getInstance().fatalBusinessException(MsgCode.CERTIFY_NAME_FAIL, e.getMessage());
            }

            IdentifierVo identifierVo =Json.fromJson(Json.parse(returnStr), IdentifierVo.class);
            if (!"100".equals(identifierVo.ResponseCode)) {
                throw CommonUtil.getInstance().fatalBusinessException(MsgCode.CERTIFY_NAME_FAIL, "失败code:" + identifierVo.ResponseCode);
            }else{
                if (!"一致".equals(identifierVo.getResult())) {
                    throw CommonUtil.getInstance().errorBusinessException(MsgCode.CERTIFY_INFO_FAIL,identifierVo.getResult());
                }
            }
        }
    }
}
